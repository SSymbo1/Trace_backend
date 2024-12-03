package edu.hrbu.trace_backend_business.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import edu.hrbu.trace_backend_business.entity.enums.Format;

import java.util.ArrayList;
import java.util.List;

public class DateUtil {

    public static List<String> getRangeBeforeNowDateList(int days) {
        DateTime currentTime = new DateTime(DateTime.now());
        DateTime beforeTime = currentTime.offset(DateField.DAY_OF_MONTH, days);
        currentTime = new DateTime(DateTime.now());
        List<DateTime> range = cn.hutool.core.date.DateUtil.rangeToList(
                beforeTime,
                currentTime,
                DateField.DAY_OF_YEAR
        );
        List<String> timeRange = new ArrayList<>();
        range.forEach(time -> timeRange.add(time.toString(Format.FULL_DATE_FORMAT.getValue())));
        return timeRange;
    }

}
