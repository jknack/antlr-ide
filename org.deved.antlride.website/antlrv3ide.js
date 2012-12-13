/*
 * Ext JS Library 2.0.2
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
var tabs;

function selectTab(tabId) {
	tabs.activate(tabId);
}

Ext.onReady(function(){	
	
	function loadNews() {
		Ext.state.Manager.setProvider(new Ext.state.CookieProvider());    
		 // this could be inline, but we want to define the Plant record
    	// type so we can add records dynamically
	    var NewData = Ext.data.Record.create([
           // the "name" below matches the tag name to read, except "availDate"
           // which is mapped to the tag "availability"
           {name: 'topic', type: 'string'},
           {name: 'topicDate', type: 'date', dateFormat: 'm.d.Y'},
           {name: 'topicMessage', type: 'string'},
		   {name: 'download', type: 'string'}
      	]);
		
		var reader = new Ext.data.XmlReader({
               // records will have a "plant" tag
               record: 'news'
           }, NewData);
		
		// create the data store
		var store = new Ext.data.GroupingStore({
			url: 'news.xml',
            reader: reader,
            sortInfo:{field: 'topicDate', direction: "DESC"},
            groupField: 'topicDate'
        });
		
		//store.loadData(myData);
		// trigger the data store load
	    store.load();
	    //create the view
		var groupView = new Ext.grid.GroupingView({
    	        forceFit:true,
				autoFill:true,
				showGroupName: false,
        	    groupTextTpl: '{text}',
				enableRowBody:true,
	            showPreview:true,
				getRowClass : function(record, rowIndex, p, store){
	                if(this.showPreview){
                	    p.body = '<p>'+record.data.topicMessage+'</p>';
            	        return 'x-grid3-row-expanded';
        	        }
    	            return 'x-grid3-row-collapsed';
	            }
        });
		// pluggable renders
	    function renderTopic(value, p, record){
			return '<span style=\'color: #15428b; font:bold 11px tahoma,arial,sans-serif;\'>'+ record.data.topic +'</span>';
		}		
		
		function renderDownload(value, p, record){
			var download = '' + value;
			if(download.length > 0) {
				return '<a href=\'javascript:selectTab(\"download\")\'>Download</a>';
			}
			return download;
		}
		// create the Grid
		var grid = new Ext.grid.GridPanel({
			store: store,
			columns: [
				{header: "Topic", width: 250, sortable: true, renderer: renderTopic, dataIndex: 'topic'},				
				{header: "Download", width: 75, renderer: renderDownload, dataIndex: 'download'},
				{header: "Date", width: 75, hidden: true, renderer: Ext.util.Format.dateRenderer('m.d.Y'), dataIndex: 'topicDate'}
			],
			/*bbar: new Ext.PagingToolbar({
    	        pageSize: 1,
	            store: store,
            	displayInfo: true,
        	    displayMsg: 'Displaying news {0} - {1} of {2}',
    	        emptyMsg: "No news to display"
	        }),*/
			view: groupView,
			enableHdMenu: false,
			hideHeaders: true,
			stripeRows: true,
			title: 'News',
			frame: true,			
			height : 500,
			width:750
		});	    
		grid.render('newsGrid');	
		grid.getSelectionModel().selectFirstRow();
	}
	
	function loadVersions() {
	  new Ext.Panel({
        title: 'Eclipse 3.7 - Indigo',
        collapsible: false,
        renderTo: 'versions',
        width:750,
        autoLoad: 'currentversion.html'
      });
	  new Ext.Panel({
	        title: 'Eclipse 3.6 - Helios',
	        collapsible: true,
	        collapsed: true,
	        renderTo: 'versions',
	        width:750,
	        autoLoad: 'resources/previous.html'
	      });
	  new Ext.Panel({
	        title: 'Eclipse 3.3, 3.4 or 3.5',
	        collapsible: true,
	        collapsed: true,
	        renderTo: 'versions',
	        width:750,
	        autoLoad: 'resources/older.html'
	      });
	}
	
    // second tabs built from JS
    tabs = new Ext.TabPanel({
        renderTo: document.body,
        activeTab: 0,
        width:800,
        height : 800,
        plain:true,
        defaults:{autoScroll: false},
        items:[{
                title: 'Welcome',
				iconCls: 'tabs',
                autoLoad: {url: 'news.html', callback: loadNews, scope: this, scripts: true}
            },
            {
				id: 'download',
                title: 'Download/Install',
                autoLoad: {url: 'download.html', callback:loadVersions, scope: this, scripts: true}
            },{
                title: 'Documentation',
                autoLoad: {url: 'screenshots.html'}
            },
			{
                title: 'License/Acknowledgements',
                autoLoad: {url: 'license.html'}
            }
        ]
    });
});