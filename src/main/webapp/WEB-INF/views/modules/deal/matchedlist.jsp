<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<div id="matched_remove">
	<form:form id="inputForm" modelAttribute="details" name="inputForm" action="${ctx}/deal/tradeOrder/detailsmatched" method="post" class="form-horizontal form-search">
	    <input id="pageNo" name="pageNo" type="hidden" value="${orderpage.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${orderpage.pageSize}"/>
		<%-- <c:if test="${okshow eq 'show'}">
			<ul class="ul-form" style="/* display:none; */">
				<c:if test="${ok eq 'demand' }">
				<li><label>采购单号：</label>
				<form:input path="demandid" htmlEscape="false" maxlength="64" class="input-xlarge" /></li>
				<li class="btns">&nbsp;&nbsp;&nbsp;&nbsp;<input id="btnSubmit"
					class="btn btn-primary" type="submit" value="查询" /></li>
				<li class="clearfix"></li>
				</c:if>
				<c:if test="${ok eq 'supply' }">
				<li><label>供应单号：</label>
				<form:input path="supplyid" htmlEscape="false" maxlength="64" class="input-xlarge" /></li>
				<li class="btns">&nbsp;&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" /></li>
				<li class="clearfix"></li>		
	            </c:if>				
			</ul>
		</c:if> --%>
		<sys:message content="${message}"/>
		<fieldset style="margin-top:10px;">
			<legend class="alert alert-success" style="height:15px;line-height:15px;">已撮合列表</legend>
              <div class="accordion" id="accordion2">
                <!-- 开始 -->
                 <c:choose>
                 <c:when test="${!empty orderpage.list}">
                    <c:forEach items="${orderpage.list}" var="order">              
                <div class="accordion-group">
                  <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne2${order.tradeOrderSupplyDemand.id}">
                                                                              下单日期：<fmt:formatDate value="${order.tradeOrder.addDate}" pattern="yyyy-MM-dd"/>  订单编号： ${order.tradeOrder.orderNumber}    联系人：${order.addressInfo.username}     联系电话：${order.addressInfo.telephone}
                    </a>
                  </div>
                  <div id="collapseOne2${order.tradeOrderSupplyDemand.id}" class="accordion-body collapse ">
                    <div class="accordion-inner">
                     <table id="contentTable" class="table table-striped table-bordered table-condensed">
                        <thead>
                          <tr class="thcenter"><th>货品</th><th>单价(元)</th><th>数量(吨)</th><th>总金额(元)</th><th></th></tr>
                        </thead>
                        <tbody class="ulstyle" id="order${order.tradeOrder.id}">
                          <tr class="tdcenter"><td> ${order.tradeOrderSupplyDemand.pgoodsName}  ${order.tradeOrderSupplyDemand.goodsName}</td><td>${order.tradeOrderSupplyDemand.perPrice}</td><td>${order.tradeOrderSupplyDemand.number}</td><td>${order.tradeOrderSupplyDemand.totalPrice}</td><td><a href="##" onclick="querylogistics();">查看物流</a>&nbsp;&nbsp;&nbsp;<a href="##" onclick="cancelmatch();">取消撮合</a>&nbsp;&nbsp;&nbsp;<a href="##" onclick="orderclose();">交易关闭</a>&nbsp;&nbsp;&nbsp;<a href="#" id="show${order.tradeOrder.statue}" onclick="orderdetails('${order.tradeOrder.id}','${order.tradeOrder.orderNumber}');" >订单详情</a></td></tr>                                     
                        </tbody>
                     </table>
                    </div>
                  </div>
                </div>
                </c:forEach>
                 <div class="pagination">${orderpage}</div>
                 </c:when>
                 <c:otherwise>
                                                                暂无数据!
                 </c:otherwise>
               </c:choose>             
              </div>
		</fieldset>
	    </form:form>
	    </div>