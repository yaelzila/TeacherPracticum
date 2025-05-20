package testSaveFile;

import HandleStoreFiles.ForSaving;

import java.util.List;

public class PersonForTestSaving implements ForSaving {
    String id;
    String name;

    public PersonForTestSaving(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getFolderName() {
        return getClass().getPackageName();
    }

    public String getFileName() {
        return getClass().getSimpleName() + id;
    }

    public String getData() {
        return id+","+name;
    }

    @Override
    public void parseFromFile(List<String> values) {

    }
}
