package com.cmgzs.Tags.impl;

import com.cmgzs.Tags.Tag;
import lombok.Data;

/**
 * 块标签
 * \begin{}
 * ```
 * \end{}
 */
@Data
public class Yuan implements Tag {
    private final String tagName = "\\begin{class_name}\n" +
            "content \n" +
            "\\end{class_name}\n";
    /**
     * 类型
     */
    private String class_name;
    /**
     * 内容段
     */
    private String content;

    public Yuan(String class_name, String content) {
        this.class_name = class_name;
        this.content = content;
    }
    /**
     * 替换参数占位符，返回带参数的标签字符串
     *
     * @return 经过替换后的字符串
     */
    @Override
    public String getTagString() {
        return tagName.replace("class_name", class_name).replace("content", content);
    }


    public static void main(String[] args) {
        Yuan yuan = new Yuan("document",
                "\n" +
                        "% 标题\n" +
                        "\\title{A LaTeX Example}\n" +
                        "% 作者\n" +
                        "\\author{Kailugaji}\n" +
                        "\\maketitle\n" +
                        "% 章节\n" +
                        "\\section{Model}\n" +
                        "This is an equation (\\ref{eq:1}) and the picture is shown in Fig. \\ref{fig:1},\n" +
                        "where $AB^2$ is xxxxx.\n" +
                        "\n" +
                        "% 公式\n" +
                        "\\begin{equation}\n" +
                        "    \\label{eq:1}\n" +
                        "    \\begin{aligned}\n" +
                        "        AB^2 = BC^2 + AC^2\n" +
                        "    \\end{aligned}\n" +
                        "\\end{equation}\n" +
                        "\n" +
                        "% 二级标题\n" +
                        "\\subsection{二级标题}\n" +
                        "This is a table, which is shown in Table\\ref{tab:1},and this is a reference \\cite{ref:1}.\n" +
                        "\n" +
                        "% 三级标题\n" +
                        "\\subsubsection{三级标题}\n" +
                        "三级标题\n" +
                        "\n" +
                        "% 表格\n" +
                        "\\begin{table}\n" +
                        "    \\begin{threeparttable}\n" +
                        "        \\centering\n" +
                        "        \\caption{Blogs kailugaji}\n" +
                        "        \\setlength{\\tabcolsep}{5mm}{\n" +
                        "            \\begin{tabular}{ccccp{0.1mm}ccc}\n" +
                        "                \\hline \\noalign{\\smallskip}\n" +
                        "                \\multirow{3}[4]{*}{Blogs} & \\multirow{3}[4]{*}{Lists} & \\multicolumn{2}{c}{Class1}          &                        & \\multicolumn{3}{c}{Class2}                                                                            \\\\\n" +
                        "                \\cline{3-4}\\cline{6-8}    &                           & \\multirow{2}[2]{*}{\\tabincell{c}{E1                                                                                                                                  \\\\(f=0.4)}} & \\multirow{2}[2]{*}{\\tabincell{c}{E2\\\\(f=0.5)}} &       & \\multirow{2}[2]{*}{E3} & \\multicolumn{1}{c}{\\multirow{2}[2]{*}{E4}} & \\multicolumn{1}{c}{\\multirow{2}[2]{*}{E5}} \\\\\n" +
                        "                                          &                           &                                     &                        &                            &                        &                        &                        \\\\\n" +
                        "                \\noalign{\\smallskip} \\hline \\noalign{\\smallskip}\n" +
                        "                \\multirow{3}[1]{*}{A}     & A1                        & 1                                   & 3                      &                            & 2                      & \\multirow{3}[1]{*}{--} & \\multirow{3}[1]{*}{--} \\\\\n" +
                        "                                          & A2                        & 2                                   & 4                      &                            & 0                      &                        &                        \\\\\n" +
                        "                                          & A3                        & 5                                   & 9                      &                            & 7                      &                        &                        \\\\\n" +
                        "                \\noalign{\\smallskip}\n" +
                        "                \\multirow{3}[0]{*}{B}     & B1                        & \\multirow{3}[0]{*}{--}              & \\multirow{3}[0]{*}{--} &                            & \\multirow{3}[0]{*}{--} & 1                      & 4                      \\\\\n" +
                        "                                          & B2                        &                                     &                        &                            &                        & 2                      & 2                      \\\\\n" +
                        "                                          & B3                        &                                     &                        &                            &                        & 5                      & 9                      \\\\\n" +
                        "                \\noalign{\\smallskip}\n" +
                        "                \\multirow{3}[0]{*}{C}     & C1                        & 8                                   & 4                      &                            & 8                      & \\multirow{3}[0]{*}{--} & \\multirow{3}[0]{*}{--} \\\\\n" +
                        "                                          & C2                        & 3                                   & 6                      &                            & 3                      &                        &                        \\\\\n" +
                        "                                          & C3                        & 2                                   & 1                      &                            & 5                      &                        &                        \\\\\n" +
                        "                \\noalign{\\smallskip} \\hline\n" +
                        "            \\end{tabular}}\n" +
                        "        \\begin{tablenotes}\n" +
                        "            \\item[1] xxxxxxxxx.\n" +
                        "            % \\item[*] xxxxxxxxx.\n" +
                        "        \\end{tablenotes}\n" +
                        "        \\label{tab:1}\n" +
                        "    \\end{threeparttable}\n" +
                        "\\end{table}\n" +
                        "\n" +
                        "% 参考文献\n" +
                        "\\begin{thebibliography}{}\n" +
                        "    \\bibitem{ref:1}\n" +
                        "    Kailugaji, Blog about LaTex, Journal, Volume, page numbers (2020),\n" +
                        "    \\url{https://www.cnblogs.com/kailugaji/}\n" +
                        "\\end{thebibliography}");

        System.out.println(yuan.getTagString());

    }
}
