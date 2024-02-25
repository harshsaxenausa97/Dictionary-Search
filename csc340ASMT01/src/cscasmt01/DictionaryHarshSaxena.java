package cscasmt01;

import java.util.*;

public class DictionaryHarshSaxena {

    enum Keyword{

        Placeholder, CSC210, CSC220, CSC340, verb
    }

    enum PartOfSpeech{
        adjective, adverb, conjuction, interjection, noun, preposition, pronoun, verb
    }

    public enum Definition{

        Definition1(Keyword.CSC210, PartOfSpeech.adjective, "Comfortable with Objects and Classes."),
        Definition10(Keyword.Placeholder, PartOfSpeech.adjective, "To be updated..."),
        Definition11(Keyword.Placeholder, PartOfSpeech.adverb, "To be updated..."),
        Definition12(Keyword.Placeholder, PartOfSpeech.conjuction, "To be updated..."),
        Definition13(Keyword.Placeholder, PartOfSpeech.interjection, "To be updated..."),
        Definition14(Keyword.Placeholder, PartOfSpeech.noun, "To be updated..."),
        Definition15(Keyword.Placeholder, PartOfSpeech.noun, "To be updated..."),
        Definition16(Keyword.Placeholder, PartOfSpeech.noun, "To be updated..."),
        Definition17(Keyword.Placeholder, PartOfSpeech.preposition, "To be updated..."),
        Definition18(Keyword.Placeholder, PartOfSpeech.pronoun, "To be updated..."),
        Definition19(Keyword.Placeholder, PartOfSpeech.verb, "To be updated..."),
        Definition2(Keyword.CSC210, PartOfSpeech.adjective, "Ready for CSC 220."),
        Definition20(Keyword.CSC340, PartOfSpeech.adjective, "= C++ version of CSC210 + CSC220 + more."),
        Definition21(Keyword.CSC340, PartOfSpeech.noun, "A CS upper division course."),
        Definition22(Keyword.CSC340, PartOfSpeech.noun, "Many hours outside of class."),
        Definition23(Keyword.CSC340, PartOfSpeech.noun, "Programming Methodology."),
        Definition3(Keyword.CSC210, PartOfSpeech.noun, "Introduction to Java."),
        Definition4(Keyword.CSC210, PartOfSpeech.verb, "To learn Java."),
        Definition5(Keyword.CSC220, PartOfSpeech.adjective, "Ready to create complex data structures."),
        Definition6(Keyword.CSC220, PartOfSpeech.noun, "Data Structures."),
        Definition7(Keyword.CSC220, PartOfSpeech.verb, "To create data structures."),
        Definition8(Keyword.verb, PartOfSpeech.noun, "Verb is a word or group of words that expresses");

        private String definition;
        private Keyword keyword;
        private PartOfSpeech partOfSpeech;

        Definition(Keyword keyword, PartOfSpeech partOfSpeech, String definition) {
            this.definition = definition;
            this.keyword = keyword;
            this.partOfSpeech = partOfSpeech;
        }


        @Override
        public String toString() {
            return this.keyword + " [" + this.partOfSpeech + "] : " + this.definition;
        }


    }


    public HashMap<String,ArrayList<Definition>> loadData(){
        HashMap<String,ArrayList<Definition>> dictionary = new HashMap<String,ArrayList<Definition>>();

        for(Definition definition : Definition.values()){
            String key = definition.keyword.name().toLowerCase();
            if(dictionary.containsKey(key)){
                ArrayList<Definition> definitionList = dictionary.get(key);
                definitionList.add(definition);
            }
            else{
                ArrayList<Definition> definitionList = new ArrayList<Definition>();
                definitionList.add(definition);
                dictionary.put(key,definitionList);
            }

        }

        return dictionary;

    }


    public boolean containsValue(String str) {
        for (PartOfSpeech value : PartOfSpeech.values()) {
            if(str.equals(value.toString())){
                return true;
            }
        }
        return false;
    }

    public boolean containsKey(String str) {
        for (Keyword value : Keyword.values()) {
            if(str.equals(value.name().toLowerCase())){
                return true;
            }
        }
        return false;
    }

    public void filterDistinct(ArrayList<Definition> definitions){
        Set<String> definitionSet = new LinkedHashSet<String>();
        for(Definition def : definitions){
            definitionSet.add(def.toString());
        }
        System.out.println("\t|");
        for(String value : definitionSet){
            System.out.println("\t" + value);
        }
        System.out.println("\t|");
    }


    public boolean search(String[] params,  HashMap<String,ArrayList<Definition>> dictionary){

        String param1 = "";
        String param2 = "";
        String param3 = "";

        switch(params.length){
            case 1: {
                param1 = params[0].toLowerCase();
                param2 = "";
                param3 = "";
                break;
            }
            case 2:{
                param1 = params[0].toLowerCase();
                param2 = params[1];
                param3 = "";
                break;
            }
            case 3:{
                param1 = params[0].toLowerCase();
                param2 = params[1];
                param3 = params[2];
                break;
            }
        }


        ArrayList<Definition> definitions;

        if(containsKey(param1)){
           definitions = dictionary.get(param1);
        }
        else{
            System.out.println("\t|");
            System.out.println("\t<Not found>");
            System.out.println("\t|");
            return true;
        }

        if(param2.length() > 0 && (param2.equals("distinct") || containsValue(params[1]))){
            // filter part of speech
            if(containsValue(param2)){
                Iterator defIterator = definitions.iterator();
                while(defIterator.hasNext()){
                    Definition definition = (Definition) defIterator.next();
                    if(!definition.partOfSpeech.equals(PartOfSpeech.valueOf(params[1]))){
                        defIterator.remove();
                    }
                }
            }
            else if(param2.equals("distinct")){
                filterDistinct(definitions);
                return true;
            }
        }
        else{
            if(param2.length() > 0){
                System.out.println("\t|");
                System.out.println("\t<2nd argument must be part of speech or \"distinct\"> ");
                System.out.println("\t|");
                return true;
            }



        }

        // distinct filter
        if(param3.length() > 0 && param3.equals("distinct")){
            filterDistinct(definitions);
        }
        else{
            System.out.println("\t|");
            if(definitions.size() > 0){
                for(Definition def : definitions){
                    System.out.println("\t" + def.toString());
                }
            }
            else{
                System.out.println("\t<Not found>");
            }
            System.out.println("\t|");
        }


        return true;


    }


    public static void main(String args[]){

        System.out.println("run:");
        System.out.println("! Loading data...");
        DictionaryHarshSaxena dict = new DictionaryHarshSaxena();
        System.out.println("! Loading completed...");
        System.out.println("-----DICTIONARY 340 JAVA-----\n");


        Scanner sc = new Scanner(System.in);
        System.out.print("Search : ");
        while(sc.hasNextLine()){
            String arg = sc.nextLine();
            if(arg.equals("!q")){
                System.out.println("\n-----THANK YOU-----");
                break;
            }
            else{
                String[] params = arg.split(" ");
                HashMap<String,ArrayList<Definition>> dictionary = dict.loadData();
                dict.search(params,dictionary);
            }

            System.out.print("Search : ");
        }

    }

}