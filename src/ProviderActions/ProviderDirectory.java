package ProviderActions;

import Records.SummaryRecords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class ProviderDirectory {
    public static void viewDirectory() {
        ArrayList<String> names = new ArrayList<>();
        for (Map.Entry<String, Map.Entry<String, String>> entry : SummaryRecords.getInstance().summary.entrySet()) {
            names.add(entry.getValue().getKey());
        }
        Collections.sort(names);
        StringBuilder Str = new StringBuilder();
        for (String name : names) {
            for (Map.Entry<String, Map.Entry<String, String>> entry : SummaryRecords.getInstance().summary.entrySet()) {
                if (entry.getValue().getKey().equals(name)){
                    if ((entry.getValue().getValue()).matches("(0|[1-9]\\d*)")) {
                        Str.append(STR."\{String.format("%1$6s", entry.getKey())} \{String.format("%1$10s", entry.getValue().getKey())} \{String.format("%1$5s", entry.getValue().getValue())}\n");
                    }
                    else {
                        System.out.println(STR."\{String.format("%1$6s", entry.getKey())} \{String.format("%1$10s", entry.getValue().getKey())} \{String.format("%1$5s", entry.getValue().getValue())}");
                    }
                }
            }
        }
        System.out.println(Str);
    }
}

