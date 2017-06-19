import java.util.ArrayList;
import java.util.Formatter;
import java.util.Objects;
//
public class Params {
    //флаги
    private String _o = "-o", _c = "-c", _n = "-n";
    //имя входного файла
    public String outFile = "";
    //входные файлы
    public ArrayList<String> inFileNames = new ArrayList<>();
    //количество строк и символов
    public int c = -1, n = -1;
    //возвращает индекс элемента массива. Нужен для того, чтобы определить место флага
    private int GetIndex(String[] args, String val) {
        for (int i = 0; i < args.length; i++) {
            if (Objects.equals(args[i], val))
                return i;
        }
        return -1;
    }
    //показывает как заполнились параметры (для отладки)
    public String ToStr() {
        Formatter f1 = new Formatter();
        for (String item : inFileNames)
            f1.format(" %s ", item);
        Formatter f2 = new Formatter();
        return f2.format("c:%d n:%d out:%s in:%s", c, n, outFile, f1.toString()).toString();
    }
    //конструктор, отвечает за логику заполнения параметров
    Params(String args[]) throws Exception {
        if (!Objects.equals(args[0], "tail"))
            throw new Exception("Unknown command!");
        else {
            int i1 = GetIndex(args, _c);
            int i2 = GetIndex(args, _n);
            if (GetIndex(args, _c) == -1 || GetIndex(args, _n) == -1) {
                //заполнение параметров по флагам
                for (int i = 0; i < args.length; i++) {
                    if (Objects.equals(args[i], _o)) {
                        outFile = args[i + 1];
                        i++;
                    } else if (Objects.equals(args[i], _c)) {
                        c = Integer.parseInt(args[i + 1].toString());
                        i++;
                    } else if (Objects.equals(args[i], _n)) {
                        n = Integer.parseInt(args[i + 1].toString());
                        i++;
                    } else if (!Objects.equals(args[i], "tail")) {
                        inFileNames.add(args[i]);
                    }
                }

                if (n == 0 && c == 0)
                    n = 10;

            } else
                throw new Exception("You set -n and -c flags at the same time!");
        }

    }
}