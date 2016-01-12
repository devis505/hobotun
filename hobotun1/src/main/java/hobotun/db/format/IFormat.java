package hobotun.db.format;

import hobotun.db.format.table.FormatTabl;

import java.util.List;

public interface IFormat {
    
    public List<FormatTabl> getAllFormat();

    public FormatTabl getFormatById(Integer id);

    public void updateFormat(FormatTabl format);

    public void insertFormat(FormatTabl format);
}
