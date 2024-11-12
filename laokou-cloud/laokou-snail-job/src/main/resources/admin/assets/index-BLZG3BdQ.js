import{a as V,b as G,u as K,c as H,N as D,d as Q}from"./search-form.vue_vue_type_script_setup_true_lang-Hz521IbE.js";import{d as X,_ as J,a as Y}from"./download-ClVZJ3Li.js";import{d as A,Q as Z,R as ee,o as C,c as oe,w as i,f as a,h as e,$ as t,X as te,by as ae,s as le,a6 as ne,cs as re,C as se,b as ie,ae as S,B as _,g as T,t as W,bC as ce,a9 as ue,P as pe,U as me,bh as $,dp as de,O as fe,ag as we}from"./index-BztLELzb.js";import{c as ge,d as ke,e as he,g as _e}from"./workflow-CNPsY-Xm.js";import{_ as ye}from"./status-switch.vue_vue_type_script_setup_true_lang-CrGBHwP5.js";import{u as xe}from"./auth-Dc2mH7ta.js";import{_ as be}from"./select-group.vue_vue_type_script_setup_true_lang-DXA2Evh-.js";import{_ as ve}from"./Grid-DDhjb_3_.js";import"./Progress-DJwd4cUk.js";import"./group-XSuFGJhJ.js";const Ne=A({name:"WorkflowSearch",__name:"workflow-search",props:{model:{required:!0},modelModifiers:{}},emits:Z(["reset","search"],["update:model"]),setup(f,{emit:y}){const p=y,c=ee(f,"model");function x(){p("reset")}function k(){p("search")}return(b,s)=>{const w=V,v=le,m=ne,N=G;return C(),oe(N,{model:c.value,onSearch:k,onReset:x},{default:i(()=>[a(w,{span:"24 s:12 m:6",label:e(t)("page.workflow.groupName"),path:"groupName",class:"pr-24px"},{default:i(()=>[a(be,{value:c.value.groupName,"onUpdate:value":s[0]||(s[0]=n=>c.value.groupName=n)},null,8,["value"])]),_:1},8,["label"]),a(w,{span:"24 s:12 m:6",label:e(t)("page.workflow.workflowName"),path:"workflowName",class:"pr-24px","label-width":100},{default:i(()=>[a(v,{value:c.value.workflowName,"onUpdate:value":s[1]||(s[1]=n=>c.value.workflowName=n),placeholder:e(t)("page.workflow.form.workflowName")},null,8,["value","placeholder"])]),_:1},8,["label"]),a(w,{span:"24 s:12 m:6",label:e(t)("page.workflow.workflowStatus"),path:"workflowStatus",class:"pr-24px"},{default:i(()=>[a(m,{value:c.value.workflowStatus,"onUpdate:value":s[2]||(s[2]=n=>c.value.workflowStatus=n),placeholder:e(t)("page.workflow.form.workflowStatus"),options:e(te)(e(ae))},null,8,["value","placeholder","options"])]),_:1},8,["label"])]),_:1},8,["model"])}}}),Se={class:"min-h-500px flex-col-stretch gap-16px overflow-hidden lt-sm:overflow-auto"};function R(f){return typeof f=="function"||Object.prototype.toString.call(f)==="[object Object]"&&!we(f)}const Ie=A({name:"workflow_task",__name:"index",setup(f){const{hasAuth:y}=xe(),p=re(),c=se(),{columns:x,columnChecks:k,data:b,getData:s,loading:w,mobilePagination:v,searchParams:m,resetSearchParams:N}=K({apiFn:ge,apiParams:{page:1,size:10,workflowName:null,groupName:null,workflowStatus:null},columns:()=>[{type:"selection",align:"center",width:48},{key:"id",title:t("common.index"),align:"center",width:120},{key:"workflowName",title:t("page.workflow.workflowName"),align:"left",minWidth:120,render:o=>{function l(){B(o.id)}return a(_,{text:!0,tag:"a",type:"primary",onClick:l,class:"ws-normal"},{default:()=>[o.workflowName]})}},{key:"groupName",title:t("page.workflow.groupName"),align:"left",minWidth:120},{key:"nextTriggerAt",title:t("page.workflow.nextTriggerAt"),align:"left",minWidth:120},{key:"workflowStatus",title:t("page.workflow.workflowStatus"),align:"left",minWidth:120,render:o=>{const l=async(r,h)=>{var g;const{error:u}=await _e(o.id);u||(o.workflowStatus=r,(g=window.$message)==null||g.success(t("common.updateSuccess"))),h()};return a(ye,{value:o.workflowStatus,"onUpdate:value":r=>o.workflowStatus=r,onFetch:l},null)}},{key:"triggerType",title:t("page.workflow.triggerType"),align:"left",minWidth:120,render:o=>{if(!o.triggerType)return null;const l=t(ce[o.triggerType]);return a(pe,{type:ue(o.triggerType)},R(l)?l:{default:()=>[l]})}},{key:"triggerInterval",title:t("page.workflow.triggerInterval"),align:"left",minWidth:120},{key:"executorTimeout",title:t("page.workflow.executorTimeout"),align:"left",minWidth:120},{key:"updateDt",title:t("page.workflow.updateDt"),align:"left",minWidth:120},{key:"operate",title:t("common.operate"),align:"center",fixed:"right",width:200,render:o=>{let l;const r=[{label:t("common.execute"),key:"execute",click:()=>M(o.id)},{type:"divider",key:"d1"},{label:t("common.copy"),key:"copy",click:()=>O(o.id)},{type:"divider",key:"d2"},{label:t("common.batchList"),key:"batchList",click:()=>j(o.id)}];function h(u){r.filter(g=>g.key===u)[0].click()}return a("div",{class:"flex-center gap-8px"},[a(_,{text:!0,type:"warning",ghost:!0,size:"small",onClick:()=>I(o.id)},R(l=t("common.edit"))?l:{default:()=>[l]}),y("R_ADMIN")?a(me,null,[a($,{vertical:!0},null),a(D,{onPositiveClick:()=>F(o.id)},{default:()=>t("common.confirmDelete"),trigger:()=>{let u;return a(_,{text:!0,type:"error",ghost:!0,size:"small"},R(u=t("common.delete"))?u:{default:()=>[u]})}})]):"",a($,{vertical:!0},null),a(de,{trigger:"click","show-arrow":!1,options:r,size:"small","on-select":h},{default:()=>a(_,{text:!0,type:"primary",ghost:!0,size:"small"},{default:()=>[T("更多")]})})])}}]}),{checkedRowKeys:n,onBatchDeleted:P}=H(b,s);async function U(){P()}async function F(o){var r;const{error:l}=await ke(o);l||((r=window.$message)==null||r.success(t("common.deleteSuccess")),s())}function I(o){p.push({path:"/workflow/form/edit",query:{id:o}})}function z(){p.push({path:"/workflow/form/add"})}function B(o){p.push({path:"/workflow/form/detail",query:{id:o}})}function O(o){p.push({path:"/workflow/form/copy",query:{id:o}})}function j(o){p.push({path:"/workflow/batch",state:{workflowId:o}})}async function M(o){var r;const{error:l}=await he(o);l||((r=window.$message)==null||r.success(t("common.executeSuccess")),s())}function q(){return{workflowIds:n.value,groupName:m.groupName,workflowName:m.workflowName,workflowStatus:m.workflowStatus}}function E(){X("/workflow/export",q(),t("page.workflow.title"))}return(o,l)=>{const r=J,h=Y,u=Q,g=ve,L=fe;return C(),ie("div",Se,[a(Ne,{model:e(m),"onUpdate:model":l[0]||(l[0]=d=>S(m)?m.value=d:null),onReset:e(N),onSearch:e(s)},null,8,["model","onReset","onSearch"]),a(L,{title:e(t)("page.workflow.title"),bordered:!1,size:"small",class:"sm:flex-1-hidden card-wrapper","header-class":"view-card-header"},{"header-extra":i(()=>[a(u,{columns:e(k),"onUpdate:columns":l[1]||(l[1]=d=>S(k)?k.value=d:null),"disabled-delete":e(n).length===0,loading:e(w),"show-delete":!1,onAdd:z,onDelete:U,onRefresh:e(s)},{addAfter:i(()=>[a(r,{action:"/workflow/import",accept:"application/json",onRefresh:e(s)},null,8,["onRefresh"]),a(e(D),{onPositiveClick:E},{trigger:i(()=>[a(e(_),{size:"small",ghost:"",type:"primary",disabled:e(n).length===0&&e(y)("R_USER")},{icon:i(()=>[a(h,{class:"text-icon"})]),default:i(()=>[T(" "+W(e(t)("common.export")),1)]),_:1},8,["disabled"])]),default:i(()=>[T(W(e(n).length===0?e(t)("common.exportAll"):e(t)("common.exportPar",{num:e(n).length})),1)]),_:1})]),_:1},8,["columns","disabled-delete","loading","onRefresh"])]),default:i(()=>[a(g,{"checked-row-keys":e(n),"onUpdate:checkedRowKeys":l[2]||(l[2]=d=>S(n)?n.value=d:null),columns:e(x),data:e(b),"flex-height":!e(c).isMobile,"scroll-x":1300,loading:e(w),remote:"","row-key":d=>d.id,pagination:e(v),class:"sm:h-full"},null,8,["checked-row-keys","columns","data","flex-height","loading","row-key","pagination"])]),_:1},8,["title"])])}}});export{Ie as default};
