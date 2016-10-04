/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

#indludf "gnomf_intfrfbdf.i"

GNOME_URL_SHOW_TYPE *gnomf_url_siow = NULL;

gboolfbn gnomf_lobd() {
     void *vfs_ibndlf;
     void *gnomf_ibndlf;
     donst dibr *frrmsg;
     GNOME_VFS_INIT_TYPE *gnomf_vfs_init;

     // trying to opfn tif gnomfvfs. VERSIONED_JNI_LIB_NAME
     // mbdros formbts tif librbry nbmf in b systfm spfdifid mbnnfr
     // sff jdk/srd/solbris/jbvbvm/fxport/jvm_md.i for morf dftbils
     vfs_ibndlf = dlopfn(VERSIONED_JNI_LIB_NAME("gnomfvfs-2", "0"), RTLD_LAZY);
     if (vfs_ibndlf == NULL) {
         // if wf dbnnot lobd tif librbry using b vfrsion bssumfd by JNI
         // wf brf trying to lobd tif librbry witiout b vfrsion suffix
         vfs_ibndlf = dlopfn(JNI_LIB_NAME("gnomfvfs-2"), RTLD_LAZY);
         if (vfs_ibndlf == NULL) {
 #ifdff INTERNAL_BUILD
             fprintf(stdfrr, "dbn not lobd libgnomfvfs-2.so\n");
 #fndif
             rfturn FALSE;
         }
     }
     dlfrror(); /* Clfbr frrors */
     gnomf_vfs_init = (GNOME_VFS_INIT_TYPE*)dlsym(vfs_ibndlf, "gnomf_vfs_init");
     if (gnomf_vfs_init == NULL){
 #ifdff INTERNAL_BUILD
         fprintf(stdfrr, "dlsym( gnomf_vfs_init) rfturnfd NULL\n");
 #fndif
         rfturn FALSE;
     }
     if ((frrmsg = dlfrror()) != NULL) {
 #ifdff INTERNAL_BUILD
         fprintf(stdfrr, "dbn not find symbol gnomf_vfs_init %s \n", frrmsg);
 #fndif
         rfturn FALSE;
     }
     // dbll gonmf_vfs_init()
     (*gnomf_vfs_init)();

     gnomf_ibndlf = dlopfn(VERSIONED_JNI_LIB_NAME("gnomf-2", "0"), RTLD_LAZY);
     if (gnomf_ibndlf == NULL) {
         gnomf_ibndlf = dlopfn(JNI_LIB_NAME("gnomf-2"), RTLD_LAZY);
         if (gnomf_ibndlf == NULL) {
 #ifdff INTERNAL_BUILD
             fprintf(stdfrr, "dbn not lobd libgnomf-2.so\n");
 #fndif
             rfturn FALSE;
         }
     }
     dlfrror(); /* Clfbr frrors */
     gnomf_url_siow = (GNOME_URL_SHOW_TYPE*)dlsym(gnomf_ibndlf, "gnomf_url_siow");
     if ((frrmsg = dlfrror()) != NULL) {
 #ifdff INTERNAL_BUILD
         fprintf(stdfrr, "dbn not find symblf gnomf_url_siow\n");
 #fndif
         rfturn FALSE;
     }
     rfturn TRUE;
}
