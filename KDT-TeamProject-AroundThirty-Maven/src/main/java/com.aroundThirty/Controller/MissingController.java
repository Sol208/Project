package com.aroundThirty.Controller;

import com.aroundThirty.model.MissingCardDto;
import com.aroundThirty.model.MissingDao;
import com.aroundThirty.model.MissingDto;

import javax.swing.*;

import static com.aroundThirty.Resource.BR.*;


public class MissingController {
    String defaultImgPath = "src/com/aroundThirty/imgFiles/그림1.png";
    ImageIcon defaultImg = new ImageIcon(defaultImgPath);


    public MissingController() {
        showAll();
    }

    public void showAll(){
        missingListAll = MissingDao.missingSelectAll();
        if (!missingListAll.isEmpty()){
            missingDto = missingListAll.get(missingListAll.size() - 1);
        }
        for (int i = 0; i < missingListAll.size(); i++) {
            ImageIcon img = new ImageIcon(missingListAll.get(i).thumbnail_Img);
            if (missingListAll.get(i).thumbnail_Img == null) {
                missingCardDto = new MissingCardDto(defaultImg, i);
                missingCardDtoList.add(missingCardDto);
            } else {
                missingCardDto = new MissingCardDto(img, i);
                missingCardDtoList.add(missingCardDto);
            }
        }
    }
}
