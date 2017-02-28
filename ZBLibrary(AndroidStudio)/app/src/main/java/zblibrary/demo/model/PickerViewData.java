package zblibrary.demo.model;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * Created by 陈超 on 2017/2/28.
 */

public class PickerViewData implements IPickerViewData {
    private String content;

    public PickerViewData(String content) {
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getPickerViewText() {
        return content;
    }
}
