<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	  <div id="matching_remove">
	    <form:form id="inputForm" modelAttribute="details" name="inputForm" action="${ctx}/deal/tradeOrder/detailsmatching" method="post" class="form-horizontal form-search">
	    <input id="pageNo" name="pageNo" type="hidden" value="${supplydemandpage.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${supplydemandpage.pageSize}"/>
	    <fieldset>	       
			<legend class="alert alert-success" style="height:15px;line-height:15px;">待确认订单列表</legend>
              <div class="accordion" id="accordion3">
                 <!-- 开始 -->
                 <c:choose>
                 <c:when test="${!empty supplydemandpage.list}">
                   <c:forEach items="${supplydemandpage.list}" var="supplydemandpage">                                                    
                <%-- <input type="hidden" id="sdid${supplydemandpage.tradeOrderSupplyDemand.id}" value="${supplydemandpage.tradeOrderSupplyDemand.id}" >
                <input type="hidden" id="oid${supplydemandpage.tradeOrderSupplyDemand.id}" value="${supplydemandpage.tradeOrder.id}" > --%>
                <div class="accordion-group">
                  <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion3" href="#collapseOne3${supplydemandpage.tradeOrderSupplyDemand.id}">
                                                                                   进货单号:${supplydemandpage.tradeOrderSupplyDemand.id}
                    </a>
                  </div>
                  <div id="collapseOne3${supplydemandpage.tradeOrderSupplyDemand.id}" class="accordion-body collapse">
                    <div class="accordion-inner">
                       <table id="contentTable" class="table table-striped table-bordered table-condensed">
                        <thead>
                          <tr class="thcenter"><th>货品</th><th>单价(元)</th><th>数量(吨)</th><th>总金额(元)</th></tr>
                        </thead>
                        <tbody class="ulstyle">
                          <tr class="tdcenter"><td> ${supplydemandpage.tradeOrderSupplyDemand.pgoodsName} ${supplydemandpage.tradeOrderSupplyDemand.goodsName}  </td><td>${supplydemandpage.tradeOrderSupplyDemand.perPrice}</td><td> ${supplydemandpage.tradeOrderSupplyDemand.number}</td><td colspan="2">${supplydemandpage.tradeOrderSupplyDemand.totalPrice}</td><!--<td> <a href="">查看物流</a>&nbsp;&nbsp;&nbsp;<a href="">取消撮合</a>&nbsp;&nbsp;&nbsp;<a href="">交易关闭</a>&nbsp;&nbsp;&nbsp;<a href="">订单详情</a> </td>--></tr>
                         <tr class="tdcenter">
                              <td colspan="" style="border-left:none;border-right:none;border-bottom:none;text-align:right;">给卖家留言:</td>
	                          <td colspan="" style="border-left:none;border-right:none;border-bottom:none;text-align:left;"><textarea name="tosay" id="tosay" style="width:400px;">世界你好，你的商品是非常的好，我还想再买点，不知道还有没有</textarea></td>
	                          <td colspan="" style="border-left:none;border-right:none;border-bottom:none;"></td>	                          
	                          <td colspan="" style="border-left:none;border-right:none;border-bottom:none;"></td>              
		                      <td colspan="" style="border-left:none;border-right:none;border-bottom:none;"></td>   
                          </tr>
                         <tr class="tdcenter">
	                          <td colspan="" style="border:none;">供货商</td>
	                          <td colspan="" style="border:none;text-align:left;">${supplydemandpage.addressInfo.detailArea} </td>
	                          <td colspan="" style="border:none;">联系人：${supplydemandpage.addressInfo.username}&nbsp;&nbsp;&nbsp;联系电话：${supplydemandpage.addressInfo.telephone}</td>
	                          <td colspan="" style="border:none;"><a href="#" onclick="entermatch('${supplydemandpage.tradeOrderSupplyDemand.orderId}','${supplydemandpage.tradeOrderSupplyDemand.id}','${supplydemandpage.tradeOrderSupplyDemand.orderId}');">确认撮合</a></td>              
		                      <td colspan="" style="border:none;"><a href="javascript:;" onclick="deleteJhds('${supplydemandpage.tradeOrderSupplyDemand.id}')">删除进货单</a></td>   
                          </tr>
                        </tbody>
                     </table>
                    </div>
                  </div>
                </div>
                </c:forEach>
              <div class="pagination">${supplydemandpage}</div>
                 </c:when>
                 <c:otherwise>
                                                                暂无数据!
                 </c:otherwise>
               </c:choose>                
              </div>
		</fieldset>
		<!-- <div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div> -->
	</form:form>
</div>
