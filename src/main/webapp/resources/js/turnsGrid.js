$(function(){
    $("#turns_list").jqGrid({
        url:'${TurnUrl}/turnslist',
        datatype: 'json',
        mtype: 'GET',
        colNames:['${labelId}','${labelTurnTitle}','${labelTurnCategory}'],
        colModel :[
            {name:'id', index:'id', width:50, align:"center"},
            {name:'title', index:'title', width:150, align:"center"},
            {name:'turnCategory.title', index:'turnCategory.title', width:150, align:"center"}
        ],
        jsonReader : {
            root:"turnData",
            page: "currentPage",
            total: "totalPages",
            records: "totalRecords",
            repeatitems: false,
            id: "id"
        },
        emptyrecords: "${labelCategoriesEmpty}",
        pager: '#pager',
        rowNum:10,
        rowList:[10,20,30],
        sortname: 'title',
        sortorder: 'asc',
        viewrecords: true,
        width:500,
        height:275,
        caption: '${labelTurnsList}'
    });
    $("#turns_list").jqGrid('navGrid','#pager',
        {edit:false,add:false,del:false,search:false},
        {}, {}, {},
        {}
    );
    $("#turns_list").navButtonAdd('#pager',
        {  caption:"${labelAdd}",
            buttonicon:"ui-icon-plus",
            onClickButton: addRow,
            position: "last",
            title:"",
            cursor: "pointer"
        }
    );

    $("#turns_list").navButtonAdd('#pager',
        {  caption:"${labelEdit}",
            buttonicon:"ui-icon-pencil",
            onClickButton: editRow,
            position: "last",
            title:"",
            cursor: "pointer"
        }
    );

    $("#turns_list").navButtonAdd('#pager',
        {  caption:"${labelDelete}",
            buttonicon:"ui-icon-trash",
            onClickButton: deleteRow,
            position: "last",
            title:"",
            cursor: "pointer"
        }
    );
});

    $(function(){
        $("#categories_list").jqGrid({
            url:'${TurnUrl}/categorieslist',
    datatype: 'json',
    mtype: 'GET',
    colNames:['${labelId}','${labelCategoryTitle}'],
    colModel :[
                {name:'id', index:'id', width:50, align:"center"},
                {name:'title', index:'title', width:150, align:"center"}
    ],
    jsonReader : {
        root:"turnCategoryData",
        page: "currentPage",
        total: "totalPages",
        records: "totalRecords",
        repeatitems: false,
        id: "id"
        },
    emptyrecords: "${labelCategoriesEmpty}",
    pager: '#pagerCategories',
    rowNum:10,
    rowList:[10,20,30],
    sortname: 'title',
    sortorder: 'asc',
    viewrecords: true,
    width:500,
    height:275,
    caption: '${labelCategoriesList}'
    });
    //            $("#categories_list").jqGrid('navGrid','#pagerCategories',
    //                    {edit:false,add:false,del:false,search:false},
    //                    {}, {}, {},
    //                    {}
    //            );
    //
    //            $("#categories_list").navButtonAdd('#pagerCategories',
    //                    {  caption:"${labelEdit}",
    //                        buttonicon:"ui-icon-pencil",
    //                        onClickButton: editCategoryRow,
    //                        position: "last",
    //                        title:"",
    //                        cursor: "pointer"
    //                    }
    //            );

    });

    function addRow() {
        document.location.href = "${TurnUrl}/?add";
    }
    function editRow() {
        var id = $("#turns_list").jqGrid('getGridParam','selrow');
        if (id != null) {
        document.location.href = "${TurnUrl}/" + id + "?edit";
    } else {
        $( "#dialogSelectRow" ).dialog({height:100, resizable:false});
    }
    }
    function deleteRow() {
        var id = $("#turns_list").jqGrid('getGridParam','selrow');
        if (id != null) {
        $( "#dialog-confirm" ).dialog({
        resizable:false,
        height:160,
        modal: true,
        buttons: {
        "${labelYes}": function() {
        document.location.href = "${TurnUrl}/" + id + "?delete";
    },
    "${labelNo}": function() {
        $( this ).dialog( "close" );
        }
    }
    });
    } else {
        $( "#dialogSelectTurn" ).dialog({height:100, resizable:false});
    }
    }
    //        function editCategoryRow() {
        //            var id = $("#categories_list").jqGrid('getGridParam','selrow');
        //            if (id != null) {
        //                document.location.href = "${TurnUrl}/category/" + id + "?edit";
    //            } else {
        //                $( "#dialogSelectCategory" ).dialog({height:100, resizable:false});
    //            }
    //        }