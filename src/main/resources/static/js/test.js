// var xxx = `
// <tr>
//     <td th:text="${category.getId()}"></td>
//     <td th:text="${category.getCode()}"></td>
//     <td th:text="${category.getName()}"></td>
//     <td th:text="${category.getDescription()}"></td>
//     <td>
//         <a th:href="@{/admin/categories/edit/{code}/{id}(id = ${category.getId()}, code = ${category.getCode()})}"
//             class="btn-sm btn btn-inverse-secondary btn-fw btn-rounded"
//             style="padding-top: 6px; padding-bottom: 6px;">
//             Edit
//         </a>
//     </td>
//     <td>
//         <a th:href="@{/admin/categories/delete/{code}/{id}(id = ${category.getId()}, code = ${category.getCode()})}"
//             class="btn-sm btn btn-inverse-danger btn-fw btn-rounded"
//             style="padding-top: 6px; padding-bottom: 6px;">
//             Delete
//         </a>
//     </td>
// </tr>`
$('document').ready(function () {
  $.getJSON("/admin", function (data) {
    console.log(data);
  });
});
