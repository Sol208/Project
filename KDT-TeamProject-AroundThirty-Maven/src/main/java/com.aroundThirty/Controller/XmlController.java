package com.aroundThirty.Controller;

import com.aroundThirty.model.*;

import javax.swing.*;

import static com.aroundThirty.Resource.BR.*;
import static com.aroundThirty.Resource.FR.*;


public class XmlController {
    String defaultImgPath = "src/com/aroundThirty/imgFiles/그림1.png";
    ImageIcon defaultImg = new ImageIcon(defaultImgPath);


    public XmlController() {
        xmlDtoListAll = XmlDao.xmlSelectAll();
        if (!xmlDtoListAll.isEmpty()){
            xmlDto = xmlDtoListAll.get(xmlDtoListAll.size() - 1);
        }

        for (int i = 0; i < xmlDtoListAll.size(); i++) {
            ImageIcon img = new ImageIcon(xmlDtoListAll.get(i).thumbnail_Img);
            if (xmlDtoListAll.get(i).thumbnail_Img == null) {
                xmlCardDto = new XmlCardDto(defaultImg, i);
                xmlCardDtoList.add(xmlCardDto);
            } else {
                xmlCardDto = new XmlCardDto(img, i);
                xmlCardDtoList.add(xmlCardDto);
            }
        }
    }
}
