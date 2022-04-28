package com.aroundThirty.Controller;

import com.aroundThirty.model.*;

import javax.swing.*;

import static com.aroundThirty.Resource.BR.*;


public class AdoptController {
    String defaultImgPath = "src/main/java/com.aroundThirty/imgFiles/그림1.png";
    ImageIcon defaultImg = new ImageIcon(defaultImgPath);


    public AdoptController() {
        showAll();
    }

    public void showAll(){
        adoptListAll = AdoptDao.adoptSelectAll();
        if (!adoptListAll.isEmpty()){
            adoptDto = adoptListAll.get(adoptListAll.size() - 1);
        }
        for (int i = 0; i < adoptListAll.size(); i++) {
            ImageIcon img = new ImageIcon(adoptListAll.get(i).thumbnail_Img);
            if (adoptListAll.get(i).thumbnail_Img == null) {
                adoptCardDto = new AdoptCardDto(defaultImg, i);
                adoptCardDtoList.add(adoptCardDto);
            } else {
                adoptCardDto = new AdoptCardDto(img, i);
                adoptCardDtoList.add(adoptCardDto);
            }
        }
    }
}
