package com.aroundThirty.Controller;

import com.aroundThirty.model.*;

import javax.swing.*;

import static com.aroundThirty.Resource.BR.*;


public class TemporaryController {
    String defaultImgPath = "src/com/aroundThirty/imgFiles/그림1.png";
    ImageIcon defaultImg = new ImageIcon(defaultImgPath);


    public TemporaryController() {
        showAll();
    }

    public void showAll(){
        temporaryListAll = TemporaryDao.temporarySelectAll();
        if (!temporaryListAll.isEmpty()){
            temporaryDto = temporaryListAll.get(temporaryListAll.size() - 1);
        }
        for (int i = 0; i < temporaryListAll.size(); i++) {
            ImageIcon img = new ImageIcon(temporaryListAll.get(i).thumbnail_Img);
            if (temporaryListAll.get(i).thumbnail_Img == null) {
                temporaryCardDto = new TemporaryCardDto(defaultImg, i);
                temporaryCardDtoList.add(temporaryCardDto);
            } else {
                temporaryCardDto = new TemporaryCardDto(img, i);
                temporaryCardDtoList.add(temporaryCardDto);
            }
        }
    }
}
