package com.trkj.crmproject.entity.Vo;

import com.trkj.crmproject.entity.OrderFrom;
import com.trkj.crmproject.entity.QuotationDetails;
import lombok.Data;

import java.util.List;

@Data
public class AddOdrVO {
    private OrderFrom odr;
    private List<QuotationDetails> quotationDetails;
}
