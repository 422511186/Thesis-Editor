package com.cmgzs.api;

import com.cmgzs.Tags.impl.Usepackage;
import com.cmgzs.Tags.impl.Yuan;
import com.cmgzs.commons.DocumentTypes;
import com.cmgzs.pojo.Document;
import com.cmgzs.service.LaTexService;
import com.cmgzs.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.cmgzs.utils.FileUtils.deleteFile;

/**
 * @Auther: hzy
 * @Date: 2022/4/28
 * @Description: 测试
 */

@RestController
@Slf4j
public class Test {

    @Value(value = "${File.path}")
    private String path;

    @Autowired
    private LaTexService laTexServiceImpl;

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private FileUtils fileUtils;

    @GetMapping(value = "/Test")
    public String test() throws IOException, IllegalAccessException {
/*
        MongoDatabase db = mongoTemplate.getDb();
        System.out.println("db.getName() = " + db.getName());
        DocumentOption option = new DocumentOption();
        option.setDraft("setDraft");
        mongoTemplate.save(option);
        Criteria criteriaName = Criteria.where("draft").is("setDraft");
        Query query = new Query(criteriaName);
        return mongoTemplate.find(query, DocumentOption.class);*/

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
        String s = laTexServiceImpl.convert(document);
//        s += content;
        s += new Yuan("document", content).getTagString();
        String s0 = s;
        String uuid = UUID.randomUUID().toString().replace("-", "");

        //  创建临时编译文件夹
        laTexServiceImpl.Mkdir(uuid);

        File file = laTexServiceImpl.Save(uuid, s0);

        String fileName = laTexServiceImpl.Compile(uuid);

        //  自旋删除临时文件
        boolean delete = false;
        while (!delete)
            delete = deleteFile(file.getParentFile());
        fileUtils.delFiles(file);
        return "http://localhost:8080/file/" + fileName;

    }
}



