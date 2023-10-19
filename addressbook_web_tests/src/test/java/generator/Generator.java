package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.CommonFunctions;
import model.AddressData;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.runtime.ObjectMethods;
import java.util.ArrayList;
import java.util.List;

import static test.TestBase.randomFile;

public class Generator {

    @Parameter(names={"--type","-t"})
    String type;

    @Parameter(names={"--output","-o"})
    String output;

    @Parameter(names={"--format","-f"})
    String format;

    @Parameter(names={"--count","-c"})
    int count;

    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }
//--type groups --output groups.xml --format xml --count 3
//--type contacts --output contacts.json --format json --count 3
    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else {
            throw  new IllegalArgumentException("Неизвестный тип данных " + type);
        }
    }

    private Object generateGroups() {
        var result = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            result.add(new GroupData()
                    .withName(CommonFunctions.randomString(i * 10))
                    .withHeader(CommonFunctions.randomString(i * 10))
                    .withFooter(CommonFunctions.randomString(i * 10)));
        }
        return result;
    }

    private Object generateContacts() {
        var result = new ArrayList<AddressData>();
        for (int i = 0; i < count; i++) {
            result.add(new AddressData()
                    .withFirst(CommonFunctions.randomString(i * 10))
                    .withMiddle(CommonFunctions.randomString(i * 10))
                    .withLast(CommonFunctions.randomString(i * 10))
                    .withNick(CommonFunctions.randomString(i * 10))
                    .withPhoto(randomFile("src/test/resources/images"))
                    //.withPhoto("src/test/resources/images/bzz.jpg")
            );

        }
        return result;
    }

    //использование jackson
//   private void save(Object data) throws IOException {
//       if ("json".equals(format)) {
//           ObjectMapper mapper = new ObjectMapper();
//           mapper.enable(SerializationFeature.INDENT_OUTPUT);
//           mapper.writeValue(new File(output), data);
//       } else {
//           throw  new IllegalArgumentException("Неизвестный тип данных " + format);
//       }
//   }
    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            var json = mapper.writeValueAsString(data);

            try (var writer = new FileWriter(output)) {
                writer.write(json);

            }
            // writer.close();
        } else if ("yaml".equals(format)) {
            var mapper = new YAMLMapper();
            mapper.writeValue(new File(output), data);
        } else if ("xml".equals(format)) {
            var mapper = new XmlMapper();
            mapper.writeValue(new File(output), data);
        }else {
            throw  new IllegalArgumentException("Неизвестный формат данных " + format);
        }
    }

}
