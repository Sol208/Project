package com.aroundThirty.Resource;

import com.aroundThirty.model.*;

import java.util.ArrayList;
import java.util.List;

import static com.aroundThirty.Resource.FR.*;

public class BR {
    public static ArrayList<ReportCardDto> reportCardDtoList = new ArrayList<>();
    public static ArrayList<MissingCardDto> missingCardDtoList = new ArrayList<>();
    public static ArrayList<AdoptCardDto> adoptCardDtoList = new ArrayList<>();
    public static ArrayList<TemporaryCardDto> temporaryCardDtoList = new ArrayList<>();
    public static ArrayList<XmlCardDto> xmlCardDtoList = new ArrayList<>();

    public static ReportDto reportDto;
    public static List<ReportDto> reportListAll;

    public static MissingDto missingDto;
    public static List<MissingDto> missingListAll;

    public static TemporaryDto temporaryDto;
    public static List<TemporaryDto> temporaryListAll;

    public static AdoptDto adoptDto;
    public static List<AdoptDto> adoptListAll;

    public static UserDto userDto;

    public static XmlDto xmlDto;
    public static List<XmlDto> xmlDtoListAll;

    public static ReportCardDto reportCardDto;
    public static MissingCardDto missingCardDto;
    public static AdoptCardDto adoptCardDto;
    public static TemporaryCardDto temporaryCardDto;

    public static XmlCardDto xmlCardDto;



    public static int selectBtnNum;
    public static int pageNum;
    public static int report_PageNum;
    public static int missing_PageNum;
    public static int temporary_PageNum;
    public static int adopt_PageNum;
    public static int postedPageNum;
    public static int report_Posted_ListIdx;
    public static int missing_Posted_ListIdx;
    public static int temporary_Posted_ListIdx;
    public static int adopt_Posted_ListIdx;
    public static int tabPaneIdx;
}
