package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private Object generateData(Supplier<Object> dataSupplier) {
        return Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());
        //var result = new ArrayList<Object>();
        //for (int i = 0; i < count; i++) {
        //    result.add(dataSupplier.get());
        //}
        //return result;
    }
    private Object generateGroups() {
        return generateData(() -> new GroupData()
                    .withName(CommonFunctions.randomString(10))
                    .withHeader(CommonFunctions.randomString(10))
                    .withFooter(CommonFunctions.randomString(10))
            );
        }

    private Object generateContacts() {
        return generateData(() -> new ContactData()
                    .withFirst(CommonFunctions.randomString(10))
                    .withMiddle(CommonFunctions.randomString(10))
                    .withLast(CommonFunctions.randomString(10))
                    .withNick(CommonFunctions.randomString(10))
                    .withPhoto(randomFile("src/test/resources/images"))
                    //.withPhoto("src/test/resources/images/bzz.jpg")
            );

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
