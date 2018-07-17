<!DOCTYPE html>
<html lang="en">
<#include '../common/header.ftl'/>
<body>
<div id="wrapper" class="toggled">
    <#--边栏-->
    <#include "../common/nav.ftl"/>
    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>商品号</th>
                            <th>名称</th>
                            <th>图片</th>
                            <th>单价</th>
                            <th>库存</th>
                            <th>描述</th>
                            <th>类目</th>
                            <th>状态</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                <#list productInfoPage.content as productInfo>
                <tr>
                    <td>${productInfo.productId}</td>
                    <td>${productInfo.productName}</td>
                    <td><img src="${productInfo.productIcon}" style="width: 100px;height: 100px;"></td>
                    <td>${productInfo.productPrice}</td>
                    <td>${productInfo.productStock}</td>
                    <td>${productInfo.productDescription}</td>
                    <td>${productInfo.categoryType}</td>
                    <td>${productInfo.getProductStatusEnum().message}</td>
                    <td>${productInfo.createTime}</td>
                    <td>${productInfo.updateTime}</td>
                    <td><a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a></td>
                    <td>
                    <#if productInfo.productStatus==0>
                        <a href="/sell/seller/product/off_sale?productId=${productInfo.productId}">下架</a>
                        <#else>
                        <a href="/sell/seller/product/on_sale?productId=${productInfo.productId}">上架</a>
                    </#if>
                    </td>
                </tr>
                </#list>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                <#if currentPage lte 1>
                <li class="disabled">
                    <a href="#">上一页</a>
                </li>
                <#else>
                <li>
                    <a href="/sell/seller/product/list?page=${currentPage-1}">上一页</a>
                </li>
                </#if>
                <#list 1..productInfoPage.getTotalPages() as index>
                    <#if currentPage==index>
                <li class="disabled">
                    <a href="/sell/seller/product/list?page=${index}">${index}</a>
                </li>
                    <#else>
                <li>
                    <a href="/sell/seller/product/list?page=${index}">${index}</a>
                </li>
                    </#if>
                </#list>
                <#if currentPage gte productInfoPage.getTotalPages()>
                <li class="disabled">
                    <a href="#">下一页</a>
                </li>
                <#else>
                <li>
                    <a href="/sell/seller/product/list?page=${currentPage+1}">下一页</a>
                </li>
                </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>