import{_ as se,a as de,b as ge,u as ce,c as me,N as fe,d as _e}from"./search-form.vue_vue_type_script_setup_true_lang-Hz521IbE.js";import{d as be,_ as ve,a as he}from"./download-ClVZJ3Li.js";import{d as j,Q as B,R as Q,m as Se,n as Ce,a as ye,$ as t,z as X,r as Ne,S as we,o as x,c as T,w as a,f as o,g as S,t as C,h as e,b as q,U as J,V as H,W as ee,X as oe,Y as ke,Z as De,a0 as Ge,s as te,v as Me,a1 as Re,x as xe,a2 as Pe,B as A,a3 as Ue,a4 as Te,a5 as $e,a6 as ae,a7 as Oe,y as Ve,a8 as ne,a9 as F,aa as Ie,ab as le,ac as re,P as K,C as Ae,ad as Fe,ae as O,af as Be,O as je,ag as Ee}from"./index-BztLELzb.js";import{f as ze,a as We,b as Le,c as qe,d as Je}from"./group-XSuFGJhJ.js";import{_ as Ke}from"./status-switch.vue_vue_type_script_setup_true_lang-CrGBHwP5.js";import{u as Qe}from"./auth-Dc2mH7ta.js";import{_ as Ye,a as Ze}from"./DescriptionsItem-gUlfoQM0.js";import{_ as He}from"./Grid-DDhjb_3_.js";import"./Progress-DJwd4cUk.js";const Xe=j({name:"GroupOperateDrawer",__name:"group-operate-drawer",props:B({operateType:{},rowData:{}},{visible:{type:Boolean,default:!1},visibleModifiers:{}}),emits:B(["submitted"],["update:visible"]),setup(y,{emit:_}){const i=y,c=_,d=Q(y,"visible"),{formRef:M,validate:U,restoreValidation:b}=Se(),{defaultRequiredRule:r}=Ce(),g=ye(()=>({add:t("page.groupConfig.addGroupConfig"),edit:t("page.groupConfig.editGroupConfig")})[i.operateType]),P=X([]),p=Ne(v());function v(){return{groupName:"",token:"SJ_Wyz3dmsdbDOkDujOTSSoBjGQP1BMsVnj",groupStatus:1,description:"",idGeneratorMode:2,initScene:1,groupPartition:0}}const E={groupName:[{required:!0,pattern:/^[A-Za-z0-9_-]{1,64}$/,trigger:"change",message:t("page.groupConfig.form.groupNameRule")}],token:[r],groupStatus:[r],idGeneratorMode:[r],initScene:[r],groupPartition:[r]};function $(){if(i.operateType==="add"){Object.assign(p,v());return}i.operateType==="edit"&&i.rowData&&Object.assign(p,i.rowData)}function V(){d.value=!1}async function I(){var k,u;if(await U(),i.operateType==="add"){const{groupName:h,token:l,groupStatus:n,description:m,idGeneratorMode:D,initScene:R,groupPartition:N}=p,{error:G}=await ze({groupName:h,token:l,groupStatus:n,description:m,idGeneratorMode:D,initScene:R,groupPartition:N});if(G)return;(k=window.$message)==null||k.success(t("common.addSuccess"))}else{const{groupName:h,token:l,groupStatus:n,description:m,idGeneratorMode:D,initScene:R,groupPartition:N}=p,{error:G}=await We({groupName:h,token:l,groupStatus:n,description:m,idGeneratorMode:D,initScene:R,groupPartition:N});if(G)return;(u=window.$message)==null||u.success(t("common.updateSuccess"))}V(),c("submitted")}function z(){p.token=W(32)}function W(k){const u="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";let h="SJ_";for(let l=0;l<k;l+=1){const n=Math.floor(Math.random()*u.length);h+=u.substring(n,n+1)}return h}const w=async()=>{const{data:k}=await Le();P.value=k.map(u=>String(u))};return we(d,()=>{d.value&&(w(),$(),b())}),(k,u)=>{const h=te,l=Me,n=Re,m=xe,D=Pe,R=se,N=A,G=Ue,f=Te,Y=$e,Z=ae,ue=Oe,ie=Ve,pe=ne;return x(),T(pe,{modelValue:d.value,"onUpdate:modelValue":u[7]||(u[7]=s=>d.value=s),title:g.value,onSubmitted:I},{footer:a(()=>[o(m,{size:16},{default:a(()=>[o(N,{onClick:V},{default:a(()=>[S(C(e(t)("common.cancel")),1)]),_:1}),o(N,{type:"primary",onClick:I},{default:a(()=>[S(C(e(t)("common.save")),1)]),_:1})]),_:1})]),default:a(()=>[o(ie,{ref_key:"formRef",ref:M,model:p,rules:E},{default:a(()=>[o(ue,{"default-expanded-names":["1","2"]},{default:a(()=>[o(Y,{title:e(t)("page.groupConfig.commonConfig"),name:"1"},{default:a(()=>[o(l,{label:e(t)("page.groupConfig.groupName"),path:"groupName"},{default:a(()=>[o(h,{value:p.groupName,"onUpdate:value":u[0]||(u[0]=s=>p.groupName=s),maxlength:64,"show-count":"",placeholder:e(t)("page.groupConfig.form.groupName"),disabled:i.operateType==="edit"},null,8,["value","placeholder","disabled"])]),_:1},8,["label"]),o(l,{label:e(t)("page.groupConfig.groupStatus"),path:"groupStatus"},{default:a(()=>[o(D,{value:p.groupStatus,"onUpdate:value":u[1]||(u[1]=s=>p.groupStatus=s),name:"groupStatus"},{default:a(()=>[o(m,null,{default:a(()=>[(x(!0),q(J,null,H(e(ee),s=>(x(),T(n,{key:s.value,value:s.value,label:e(t)(s.label)},null,8,["value","label"]))),128))]),_:1})]),_:1},8,["value"])]),_:1},8,["label"]),o(l,{label:e(t)("page.groupConfig.token"),path:"token"},{default:a(()=>[o(f,null,{default:a(()=>[o(h,{value:p.token,"onUpdate:value":u[2]||(u[2]=s=>p.token=s),maxlength:64,placeholder:e(t)("page.groupConfig.form.token"),disabled:i.operateType==="edit"},null,8,["value","placeholder","disabled"]),o(G,{trigger:"hover"},{trigger:a(()=>[o(N,{type:"default",ghost:"",disabled:i.operateType==="edit",onClick:z},{default:a(()=>[o(R,{class:"text-icon"})]),_:1},8,["disabled"])]),default:a(()=>[S(" "+C(e(t)("page.groupConfig.generateToken")),1)]),_:1})]),_:1})]),_:1},8,["label"]),o(l,{label:e(t)("page.groupConfig.description"),path:"description"},{default:a(()=>[o(h,{value:p.description,"onUpdate:value":u[3]||(u[3]=s=>p.description=s),type:"textarea",maxlength:256,"show-count":"",placeholder:e(t)("page.groupConfig.form.description"),clearable:"",round:""},null,8,["value","placeholder"])]),_:1},8,["label"])]),_:1},8,["title"]),o(Y,{title:e(t)("page.groupConfig.retryConfig"),name:"2"},{default:a(()=>[o(l,{label:e(t)("page.groupConfig.idGeneratorMode"),path:"idGeneratorMode"},{default:a(()=>[o(Z,{value:p.idGeneratorMode,"onUpdate:value":u[4]||(u[4]=s=>p.idGeneratorMode=s),placeholder:e(t)("page.groupConfig.form.idGeneratorMode"),options:e(oe)(e(ke))},null,8,["value","placeholder","options"])]),_:1},8,["label"]),o(l,{label:e(t)("page.groupConfig.initScene"),path:"initScene"},{default:a(()=>[o(D,{value:p.initScene,"onUpdate:value":u[5]||(u[5]=s=>p.initScene=s),name:"initScene"},{default:a(()=>[o(m,null,{default:a(()=>[(x(!0),q(J,null,H(e(De),s=>(x(),T(n,{key:s.value,value:s.value,label:e(t)(s.label)},null,8,["value","label"]))),128))]),_:1})]),_:1},8,["value"])]),_:1},8,["label"]),o(l,{label:e(t)("page.groupConfig.groupPartition"),path:"groupPartition"},{default:a(()=>[o(Z,{value:p.groupPartition,"onUpdate:value":u[6]||(u[6]=s=>p.groupPartition=s),placeholder:e(t)("page.groupConfig.form.groupPartition"),options:e(Ge)(P.value)},null,8,["value","placeholder","options"])]),_:1},8,["label"])]),_:1},8,["title"])]),_:1})]),_:1},8,["model"])]),_:1},8,["modelValue","title"])}}}),eo=j({name:"GroupDetailDrawer",__name:"group-detail-drawer",props:B({rowData:{}},{visible:{type:Boolean,default:!1},visibleModifiers:{}}),emits:["update:visible"],setup(y){const _=Q(y,"visible");return(i,c)=>{const d=Ye,M=K,U=Ze,b=ne;return x(),T(b,{modelValue:_.value,"onUpdate:modelValue":c[0]||(c[0]=r=>_.value=r),title:e(t)("page.groupConfig.detail")},{default:a(()=>[o(U,{"label-placement":"top",bordered:"",column:2},{default:a(()=>[o(d,{label:e(t)("page.groupConfig.groupName"),span:2},{default:a(()=>{var r;return[S(C((r=i.rowData)==null?void 0:r.groupName),1)]}),_:1},8,["label"]),o(d,{label:e(t)("page.groupConfig.token"),span:2},{default:a(()=>{var r;return[S(C((r=i.rowData)==null?void 0:r.token),1)]}),_:1},8,["label"]),o(d,{label:e(t)("page.groupConfig.groupStatus"),span:1},{default:a(()=>{var r;return[o(M,{type:e(F)((r=i.rowData)==null?void 0:r.groupStatus)},{default:a(()=>{var g;return[S(C(e(t)(e(Ie)[(g=i.rowData)==null?void 0:g.groupStatus])),1)]}),_:1},8,["type"])]}),_:1},8,["label"]),o(d,{label:e(t)("page.groupConfig.idGeneratorMode"),span:1},{default:a(()=>{var r;return[o(M,{type:e(F)((r=i.rowData)==null?void 0:r.idGeneratorMode)},{default:a(()=>{var g;return[S(C(e(t)(e(le)[(g=i.rowData)==null?void 0:g.idGeneratorMode])),1)]}),_:1},8,["type"])]}),_:1},8,["label"]),o(d,{label:e(t)("page.groupConfig.groupPartition"),span:1},{default:a(()=>{var r;return[S(C((r=i.rowData)==null?void 0:r.groupPartition),1)]}),_:1},8,["label"]),o(d,{label:e(t)("page.groupConfig.initScene"),span:1},{default:a(()=>{var r;return[o(M,{type:e(F)((r=i.rowData)==null?void 0:r.initScene)},{default:a(()=>{var g;return[S(C(e(t)(e(re)[(g=i.rowData)==null?void 0:g.initScene])),1)]}),_:1},8,["type"])]}),_:1},8,["label"]),o(d,{label:e(t)("page.groupConfig.description"),span:2},{default:a(()=>{var r;return[S(C((r=i.rowData)==null?void 0:r.description),1)]}),_:1},8,["label"])]),_:1})]),_:1},8,["modelValue","title"])}}}),oo=j({name:"GroupSearch",__name:"group-search",props:{model:{required:!0},modelModifiers:{}},emits:B(["reset","search"],["update:model"]),setup(y,{emit:_}){const i=_,c=Q(y,"model");function d(){i("reset")}function M(){i("search")}return(U,b)=>{const r=te,g=de,P=ae,p=ge;return x(),T(p,{model:c.value,onSearch:M,onReset:d},{default:a(()=>[o(g,{span:"24 s:12 m:6",label:e(t)("page.groupConfig.groupName"),path:"groupName",class:"pr-24px"},{default:a(()=>[o(r,{value:c.value.groupName,"onUpdate:value":b[0]||(b[0]=v=>c.value.groupName=v),placeholder:e(t)("page.groupConfig.form.groupName")},null,8,["value","placeholder"])]),_:1},8,["label"]),o(g,{span:"24 s:12 m:6",label:e(t)("page.groupConfig.groupStatus"),path:"groupStatus",class:"pr-24px"},{default:a(()=>[o(P,{value:c.value.groupStatus,"onUpdate:value":b[1]||(b[1]=v=>c.value.groupStatus=v),placeholder:e(t)("page.groupConfig.form.groupStatus"),options:e(oe)(e(ee)),clearable:""},null,8,["value","placeholder","options"])]),_:1},8,["label"])]),_:1},8,["model"])}}}),to={class:"min-h-500px flex-col-stretch gap-16px overflow-hidden lt-sm:overflow-auto"};function L(y){return typeof y=="function"||Object.prototype.toString.call(y)==="[object Object]"&&!Ee(y)}const co=j({name:"group",__name:"index",setup(y){const{hasAuth:_}=Qe(),i=Ae(),c=X(),{bool:d,setTrue:M}=Fe(!1),{columns:U,columnChecks:b,data:r,getData:g,loading:P,mobilePagination:p,searchParams:v,resetSearchParams:E}=ce({apiFn:qe,apiParams:{page:1,size:10,groupName:null,groupStatus:null},columns:()=>[{type:"selection",align:"center",width:48},{key:"index",title:t("common.index"),align:"center",width:64},{key:"groupName",title:t("page.groupConfig.groupName"),align:"left",minWidth:260,render:l=>{function n(){c.value=l||null,M()}return o(A,{text:!0,tag:"a",type:"primary",onClick:n,class:"ws-normal"},{default:()=>[l.groupName]})}},{key:"groupStatus",title:t("page.groupConfig.groupStatus"),align:"center",width:80,render:l=>{const n=async(m,D)=>{var G;const R=l.groupStatus===1?0:1,{error:N}=await Je({groupName:l.groupName,groupStatus:R});N||(l.groupStatus=m,(G=window.$message)==null||G.success(t("common.updateSuccess"))),D()};return o(Ke,{value:l.groupStatus,"onUpdate:value":m=>l.groupStatus=m,onFetch:n,disabled:_("R_USER")},null)}},{key:"idGeneratorMode",title:t("page.groupConfig.idGeneratorMode"),align:"center",width:120,render:l=>{if(l.idGeneratorMode===null)return null;const n=t(le[l.idGeneratorMode]);return o(K,{type:"primary"},L(n)?n:{default:()=>[n]})}},{key:"groupPartition",title:t("page.groupConfig.groupPartition"),align:"center",minWidth:60},{key:"initScene",title:t("page.groupConfig.initScene"),align:"center",minWidth:80,render:l=>{if(l.groupStatus===null)return null;const n=t(re[l.initScene]);return o(K,{type:F(l.initScene)},L(n)?n:{default:()=>[n]})}},{key:"updateDt",title:t("page.groupConfig.updateDt"),align:"left",width:130},{key:"description",title:t("page.groupConfig.description"),align:"left",width:130},{key:"operate",title:t("common.operate"),align:"center",width:130,render:l=>{let n;return _("R_USER")?o(J,null,null):o("div",{class:"flex-center gap-8px"},[o(A,{type:"primary",text:!0,ghost:!0,size:"small",onClick:()=>k(l.id)},L(n=t("common.edit"))?n:{default:()=>[n]})])}}]}),{drawerVisible:$,operateType:V,editingData:I,handleAdd:z,handleEdit:W,checkedRowKeys:w}=me(r,g);function k(l){W(l)}function u(){return{groupName:v.groupName,groupStatus:v.groupStatus,groupIds:w.value}}function h(){be("/group/export",u(),t("page.groupConfig.title"))}return(l,n)=>{const m=ve,D=he,R=_e,N=He,G=je;return x(),q("div",to,[o(oo,{model:e(v),"onUpdate:model":n[0]||(n[0]=f=>O(v)?v.value=f:null),onReset:e(E),onSearch:e(g)},null,8,["model","onReset","onSearch"]),o(G,{title:e(t)("page.groupConfig.title"),bordered:!1,size:"small","header-class":"view-card-header",class:"sm:flex-1-hidden card-wrapper"},{"header-extra":a(()=>[o(R,{columns:e(b),"onUpdate:columns":n[1]||(n[1]=f=>O(b)?b.value=f:null),"disabled-delete":e(w).length===0,loading:e(P),"show-delete":!1,"show-add":e(_)("R_ADMIN"),onAdd:e(z),onRefresh:e(g)},{addAfter:a(()=>[e(_)("R_ADMIN")?(x(),T(m,{key:0,action:"/group/import",accept:"application/json",onRefresh:e(g)},null,8,["onRefresh"])):Be("",!0),o(e(fe),{onPositiveClick:h},{trigger:a(()=>[o(e(A),{size:"small",ghost:"",type:"primary",disabled:e(w).length===0&&e(_)("R_USER")},{icon:a(()=>[o(D,{class:"text-icon"})]),default:a(()=>[S(" "+C(e(t)("common.export")),1)]),_:1},8,["disabled"])]),default:a(()=>[S(C(e(w).length===0?e(t)("common.exportAll"):e(t)("common.exportPar",{num:e(w).length})),1)]),_:1})]),_:1},8,["columns","disabled-delete","loading","show-add","onAdd","onRefresh"])]),default:a(()=>[o(N,{"checked-row-keys":e(w),"onUpdate:checkedRowKeys":n[2]||(n[2]=f=>O(w)?w.value=f:null),columns:e(U),data:e(r),"flex-height":!e(i).isMobile,"scroll-x":962,loading:e(P),remote:"","row-key":f=>f.id,pagination:e(p),class:"sm:h-full"},null,8,["checked-row-keys","columns","data","flex-height","loading","row-key","pagination"]),o(Xe,{visible:e($),"onUpdate:visible":n[3]||(n[3]=f=>O($)?$.value=f:null),"operate-type":e(V),"row-data":e(I),onSubmitted:e(g)},null,8,["visible","operate-type","row-data","onSubmitted"]),o(eo,{visible:e(d),"onUpdate:visible":n[4]||(n[4]=f=>O(d)?d.value=f:null),"row-data":c.value},null,8,["visible","row-data"])]),_:1},8,["title"])])}}});export{co as default};
