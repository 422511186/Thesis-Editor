package com.cmgzs.commons;


import lombok.Data;

@Data
public class TexTags {
    //导包
    public static String header = "\\documentclass{ctexart}\n" +
            "% 插入图需要加的包\n" +
            "\\usepackage{graphicx}\n" +
            "% 插入表格需要的包\n" +
            "\\usepackage{multirow}\n" +
            "\\usepackage{threeparttable}\n" +
            "\\newcommand{\\tabincell}[2]{\\begin{tabular}{@{}#1@{}}#2\\end{tabular}}\n" +
            "% 插入公式需要的包\n" +
            "\\usepackage{amsmath}\n" +
            "% 引用，自己定义颜色\n" +
            "\\usepackage[colorlinks, linkcolor=blue, anchorcolor=green, citecolor=red]{hyperref}\n" +
            "% 超链接\n" +
            "\\usepackage{url} \n";

    public static String documentclass = "\\documentclass{@{title}}\n";
    public static String usepackage = "\\usepackage{@{title}}\n";
    public static String begin = "\\begin{@{title}}\n";
    public static String end = "\\end{@{title}}\n";

    public static String section = "\\section{@{title}}@{content}\n";
    public static String subsection = "\\subsection{@{title}}\n";
    public static String subsubsection = "\\subsubsection{@{title}}\n";
    public static String title = "\\title{@{title}}\n";
    public static String author = "\\author{@{title}}\n";
    public static String maketitle = "\\maketitle\n";
    public static String label = "\\label{@{title}}\n";

}
