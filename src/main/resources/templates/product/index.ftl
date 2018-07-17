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
                    <form role="form" method="post" action="/sell/seller/product/save">
                        <div class="form-group">
                            <label>名称</label>
                            <input type="text" class="form-control" name="productName" value="${(productInfo.productName)!''}" />
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input type="text" class="form-control" name="productPrice" value="${(productInfo.productPrice)!''}" />
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input type="number" min="1" max="10000" class="form-control" name="productStock" value="${(productInfo.productStock)!''}" />
                        </div>
                        <div class="form-group">
                            <label>描述</label>
                            <input type="text" class="form-control" name="productDescription" value="${(productInfo.productDescription)!''}" />
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <img src="${(productInfo.productIcon)!''}" height="100" width="100" alt="no image">
                            <input type="text" class="form-control" name="productIcon" value="${(productInfo.productIcon)!''}" />
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <select name="categoryType" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                            <#if (productInfo.categoryType)?? && productInfo.categoryType==category.categoryType>
                                                selected
                                            </#if>
                                    >${category.categoryName}</option>
                                </#list>
                            </select>
                        </div>
                        <input type="hidden" name="productId" value="${(productInfo.productId)!''}">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>