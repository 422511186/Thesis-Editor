package com.cmgzs.service.impl;

import com.cmgzs.Tags.impl.Documentclass;
import com.cmgzs.Tags.impl.Usepackage;
import com.cmgzs.commons.DocumentOption;
import com.cmgzs.commons.DocumentTypes;
import com.cmgzs.pojo.Document;
import com.cmgzs.service.LaTexService;
import com.cmgzs.utils.TermUtils;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LaTexServiceImpl implements LaTexService {
    //    临时文件存放父目录
    @Value(value = "${File.path}")
    private String path;

    /**
     * 创建一个临时存储文件夹
     *
     * @param fileName uuid
     * @return
     */
    @Override
    public boolean Mkdir(String fileName) {
        boolean mkdirs = false;
        while (!mkdirs) {
            mkdirs = new File(path, fileName).mkdirs();
        }
        return mkdirs;
    }

    /**
     * 写入文件信息(格式Tex)
     *
     * @param fileName 文件名字 uuid
     * @param Tex      文件内容
     * @return 操作是否成功
     */
    @Override
    public File Save(String fileName, String Tex) {
        File file = new File(path + "/" + fileName, fileName + ".tex");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.append(Tex);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 编译Tex
     *
     * @param fileName 文件名字
     * @return 编译完成的PDF文件名称
     */
    @Override
    public String Compile(String fileName) {
        //  执行编译
        TermUtils.exeCmd("src/main/resources/scripts/latexmk.bat PDFS " + path + fileName + "/" + fileName);
        return fileName + ".pdf";
    }

    /**
     * 将Document文档对象整合为代码段，保存编译。
     *
     * @param document 文档对象
     * @return 代码段
     */
    @Override
    public String convert(Document document) throws IllegalAccessException {
        StringBuilder res = new StringBuilder();
        //  解析排版配置参数
        ArrayList<String> optionList = DocumentOption.getOptionList(document.getOptions());
        // 获取文档类型
        String class_name = document.getClass_name().getName();
        // 文档头
        res.append(new Documentclass(optionList, class_name).getTagString());
        // 解析包结构
        for (Usepackage p : document.getPackages()) {
            res.append(p.getTagString());
        }
        // 添加额外的配置
        res.append("\\newcommand{\\tabincell}[2]{\\begin{tabular}{@{}#1@{}}#2\\end{tabular}}\n");

        return res.toString();
    }

    /**
     * 测试 convert 函数
     *
     * @param args
     */
    @SneakyThrows
    public static void main(String[] args) {

        String content = "% 标题\n" + "\\title{A LaTeX Example}\n" + "% 作者\n" + "\\author{Kailugaji}\n" + "\\maketitle\n" + "% 章节\n" + "\\section{Model}\n" + "This is an equation (\\ref{eq:1}) and the picture is shown in Fig. \\ref{fig:1},\n" + "where $AB^2$ is xxxxx.\n" + "\n" + "% 公式\n" + "\\begin{equation}\n" + "    \\label{eq:1}\n" + "    \\begin{aligned}\n" + "        AB^2 = BC^2 + AC^2\n" + "    \\end{aligned}\n" + "\\end{equation}\n" + "\n" + "% 二级标题\n" + "\\subsection{二级标题}\n" + "This is a table, which is shown in Table\\ref{tab:1},and this is a reference \\cite{ref:1}.\n" + "\n" + "% 三级标题\n" + "\\subsubsection{三级标题}\n" + "三级标题\n" + "\n" + "% 表格\n" + "\\begin{table}\n" + "    \\begin{threeparttable}\n" + "        \\centering\n" + "        \\caption{Blogs kailugaji}\n" + "        \\setlength{\\tabcolsep}{5mm}{\n" + "            \\begin{tabular}{ccccp{0.1mm}ccc}\n" + "                \\hline \\noalign{\\smallskip}\n" + "                \\multirow{3}[4]{*}{Blogs} & \\multirow{3}[4]{*}{Lists} & \\multicolumn{2}{c}{Class1}          &                        & \\multicolumn{3}{c}{Class2}                                                                            \\\\\n" + "                \\cline{3-4}\\cline{6-8}    &                           & \\multirow{2}[2]{*}{\\tabincell{c}{E1                                                                                                                                  \\\\(f=0.4)}} & \\multirow{2}[2]{*}{\\tabincell{c}{E2\\\\(f=0.5)}} &       & \\multirow{2}[2]{*}{E3} & \\multicolumn{1}{c}{\\multirow{2}[2]{*}{E4}} & \\multicolumn{1}{c}{\\multirow{2}[2]{*}{E5}} \\\\\n" + "                                          &                           &                                     &                        &                            &                        &                        &                        \\\\\n" + "                \\noalign{\\smallskip} \\hline \\noalign{\\smallskip}\n" + "                \\multirow{3}[1]{*}{A}     & A1                        & 1                                   & 3                      &                            & 2                      & \\multirow{3}[1]{*}{--} & \\multirow{3}[1]{*}{--} \\\\\n" + "                                          & A2                        & 2                                   & 4                      &                            & 0                      &                        &                        \\\\\n" + "                                          & A3                        & 5                                   & 9                      &                            & 7                      &                        &                        \\\\\n" + "                \\noalign{\\smallskip}\n" + "                \\multirow{3}[0]{*}{B}     & B1                        & \\multirow{3}[0]{*}{--}              & \\multirow{3}[0]{*}{--} &                            & \\multirow{3}[0]{*}{--} & 1                      & 4                      \\\\\n" + "                                          & B2                        &                                     &                        &                            &                        & 2                      & 2                      \\\\\n" + "                                          & B3                        &                                     &                        &                            &                        & 5                      & 9                      \\\\\n" + "                \\noalign{\\smallskip}\n" + "                \\multirow{3}[0]{*}{C}     & C1                        & 8                                   & 4                      &                            & 8                      & \\multirow{3}[0]{*}{--} & \\multirow{3}[0]{*}{--} \\\\\n" + "                                          & C2                        & 3                                   & 6                      &                            & 3                      &                        &                        \\\\\n" + "                                          & C3                        & 2                                   & 1                      &                            & 5                      &                        &                        \\\\\n" + "                \\noalign{\\smallskip} \\hline\n" + "            \\end{tabular}}\n" + "        \\begin{tablenotes}\n" + "            \\item[1] xxxxxxxxx.\n" + "            % \\item[*] xxxxxxxxx.\n" + "        \\end{tablenotes}\n" + "        \\label{tab:1}\n" + "    \\end{threeparttable}\n" + "\\end{table}\n" + "\n" + "% 参考文献\n" + "\\begin{thebibliography}{}\n" + "    \\bibitem{ref:1}\n" + "    Kailugaji, Blog about LaTex, Journal, Volume, page numbers (2020),\n" + "    \\url{https://www.cnblogs.com/kailugaji/}\n" + "\\end{thebibliography}";

        List<String> list = Arrays.stream("colorlinks, linkcolor=blue, anchorcolor=green, citecolor=red".split(",")).collect(Collectors.toList());
        ArrayList<Usepackage> p = new ArrayList<>() {{
            add(new Usepackage(null, "graphicx"));
            add(new Usepackage(null, "multirow"));
            add(new Usepackage(null, "threeparttable"));
            add(new Usepackage(null, "amsmath"));
            add(new Usepackage((ArrayList<String>) list, "hyperref"));
            add(new Usepackage(null, "url"));
        }};
        Document document = new Document("hzy",
                UUID.randomUUID().toString().replace("-", ""),
                "test",
                DocumentTypes.CTEXART,
                null, p, null, null);

        LaTexServiceImpl service = new LaTexServiceImpl();
        String s = service.convert(document);
        s += content;
        System.out.println(s);
    }

}
