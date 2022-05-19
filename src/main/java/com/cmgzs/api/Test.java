package com.cmgzs.api;

import com.cmgzs.commons.DocumentOption;
import com.cmgzs.service.impl.LaTexServiceImpl;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.cmgzs.utils.FileUtils.deleteFile;

/**
 * @Auther: hzy
 * @Date: 2022/4/28
 * @Description: 测试
 */

@RestController
public class Test {

    @Value(value = "${File.path}")
    private String path;

    @Autowired
    private LaTexServiceImpl laTexService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping(value = "/Test")
    public List<DocumentOption> test() throws IOException {
        MongoDatabase db = mongoTemplate.getDb();
        System.out.println("db.getName() = " + db.getName());
/*
        DocumentOption option = new DocumentOption();
        option.setDraft("setDraft");
        mongoTemplate.save(option);
*/
        Criteria criteriaName = Criteria.where("draft").is("setDraft");
        Query query = new Query(criteriaName);
        return mongoTemplate.find(query, DocumentOption.class);
/*
        StringBuffer tex = new StringBuffer();
        tex.append(TexTags.header)
                .append(TexTags.begin.replace("@{title}", "document"))
                .append(TexTags.title.replace("@{title}", "黄振宇"))
                .append(TexTags.maketitle)
                .append(TexTags.section.replace("@{title}", "Model").replace("@{content}", "This is an equation (\\ref{eq:1}) and the picture is shown in Fig. \\ref{fig:1},where $AB^2$ is xxxxx.\n"))
                .append(TexTags.end.replace("@{title}", "document"));

        String uuid = UUID.randomUUID().toString().replace("-", "");
        String s0 = tex.toString();
        //  创建临时编译文件夹
        laTexService.Mkdir(uuid);

        File file = laTexService.Save(uuid, s0);

        String fileName = laTexService.Compile(uuid);

        //  自旋删除临时文件
        boolean delete = false;
        while (!delete)
            delete = deleteFile(file.getParentFile());
        return "http://localhost:8080/file/" + fileName;
*/
    }

    @GetMapping(value = "Test01")
    public String Test01() {

        String uuid = UUID.randomUUID().toString().replace("-", "");

        String s0 = "";
        //  创建临时编译文件夹
        laTexService.Mkdir(uuid);

        File file = laTexService.Save(uuid, s0);

        String fileName = laTexService.Compile(uuid);

        //  自旋删除临时文件
        boolean delete = false;
        while (!delete)
            delete = deleteFile(file.getParentFile());
        return "http://localhost:8080/file/" + fileName;
    }
}



