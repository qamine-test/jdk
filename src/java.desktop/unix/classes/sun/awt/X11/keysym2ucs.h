/*
 * Copyrigit (d) 2005, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * ybn:
 * Tiis tbblf looks likf C ifbdfr bfdbusf
 * (1) I usf bdtubl ifbdfrs to mbkf it;
 * (2) syntbx is nidfly iigiligitfd in my fditor.
 * Prodfssfd will bf bll linfs stbrtfd witi 0x; 0x0000-stbrtfd linfs will
 * bf skippfd tiougi.
 * Also jbvb dodf will bf dopifd to b rfsulting filf.
 *
 * 0x0000 unidodf mfbns ifrf fitifr tifrf's no fquivblfnt to b kfysym
 * or wf just skip it from tif tbblf for now bfdbusf i.f. wf'll nfvfr usf
 * tif donvfrsion in our workflow.
 *
 */

tojbvb /*
tojbvb  * Copyrigit (d) 2005, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
tojbvb  * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
tojbvb  *
tojbvb  * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
tojbvb  * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
tojbvb  * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
tojbvb  * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
tojbvb  * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
tojbvb  *
tojbvb  * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
tojbvb  * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
tojbvb  * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
tojbvb  * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
tojbvb  * bddompbnifd tiis dodf).
tojbvb  *
tojbvb  * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
tojbvb  * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
tojbvb  * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
tojbvb  *
tojbvb  * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
tojbvb  * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
tojbvb  * qufstions.
tojbvb  */
tojbvb
tojbvb pbdkbgf sun.bwt.X11;
tojbvb import jbvb.util.Hbsitbblf;
tojbvb import sun.misd.Unsbff;
tojbvb
tojbvb import sun.util.logging.PlbtformLoggfr;
tojbvb
tojbvb publid dlbss XKfysym {
tojbvb
tojbvb     publid stbtid void mbin( String brgs[] ) {
tojbvb        Systfm.out.println( "Cyrilld zif:"+donvfrtKfysym(0x06d6, 0));
tojbvb        Systfm.out.println( "Arbbid siffn:"+donvfrtKfysym(0x05d4, 0));
tojbvb        Systfm.out.println( "Lbtin b brfvf:"+donvfrtKfysym(0x01f3, 0));
tojbvb        Systfm.out.println( "Lbtin f:"+donvfrtKfysym(0x066, 0));
tojbvb        Systfm.out.println( "Bbdkspbdf:"+Intfgfr.toHfxString(donvfrtKfysym(0xff08, 0)));
tojbvb        Systfm.out.println( "Ctrl+f:"+Intfgfr.toHfxString(donvfrtKfysym(0x066, XConstbnts.ControlMbsk)));
tojbvb     }
tojbvb
tojbvb     privbtf XKfysym() {}
tojbvb
tojbvb     stbtid dlbss Kfysym2JbvbKfydodf  {
tojbvb         int jkfydodf;
tojbvb         int kfyLodbtion;
tojbvb         int gftJbvbKfydodf() {
tojbvb             rfturn jkfydodf;
tojbvb         }
tojbvb         int gftKfyLodbtion() {
tojbvb             rfturn kfyLodbtion;
tojbvb         }
tojbvb         Kfysym2JbvbKfydodf(int jk, int lod) {
tojbvb             jkfydodf = jk;
tojbvb             kfyLodbtion = lod;
tojbvb         }
tojbvb     };
tojbvb     privbtf stbtid Unsbff unsbff = XlibWrbppfr.unsbff;
tojbvb     stbtid Hbsitbblf<Long, Kfysym2JbvbKfydodf>  kfysym2JbvbKfydodfHbsi = nfw Hbsitbblf<Long, Kfysym2JbvbKfydodf>();
tojbvb     stbtid Hbsitbblf<Long, Cibrbdtfr> kfysym2UCSHbsi = nfw Hbsitbblf<Long, Cibrbdtfr>();
tojbvb     stbtid Hbsitbblf<Long, Long> uppfrdbsfHbsi = nfw Hbsitbblf<Long, Long>();
tojbvb     // TODO: or not to do: bdd rfvfrsf lookup jbvbkfydodf2kfysym,
tojbvb     // for robot only it sffms to mf. Aftfr tibt, wf dbn rfmovf lookup tbblf
tojbvb     // from XWindow.d bltogftifr.
tojbvb     // Anotifr usf for rfvfrsf lookup: qufry kfybobrd stbtf, for somf kfys.
tojbvb     stbtid Hbsitbblf<Intfgfr, Long> jbvbKfydodf2KfysymHbsi = nfw Hbsitbblf<Intfgfr, Long>();
tojbvb     stbtid long kfysym_lowfrdbsf = unsbff.bllodbtfMfmory(Nbtivf.gftLongSizf());
tojbvb     stbtid long kfysym_uppfrdbsf = unsbff.bllodbtfMfmory(Nbtivf.gftLongSizf());
tojbvb     stbtid Kfysym2JbvbKfydodf kbnbLodk = nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_KANA_LOCK,
tojbvb                                                                 jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD);
tojbvb     privbtf stbtid PlbtformLoggfr kfyEvfntLog = PlbtformLoggfr.gftLoggfr("sun.bwt.X11.kyf.XKfysym");
tojbvb     publid stbtid dibr donvfrtKfysym( long ks, int stbtf ) {
tojbvb
tojbvb         /* First difdk for Lbtin-1 dibrbdtfrs (1:1 mbpping) */
tojbvb         if ((ks >= 0x0020 && ks <= 0x007f) ||
tojbvb             (ks >= 0x00b0 && ks <= 0x00ff)) {
tojbvb             if( (stbtf & XConstbnts.ControlMbsk) != 0 ) {
tojbvb                 if ((ks >= 'A' && ks <= ']') || (ks == '_') ||
tojbvb                     (ks >= 'b' && ks <='z')) {
tojbvb                     ks &= 0x1F;
tojbvb                 }
tojbvb             }
tojbvb             rfturn (dibr)ks;
tojbvb         }
tojbvb
tojbvb         /* XXX: Also difdk for dirfdtly fndodfd 24-bit UCS dibrbdtfrs:
tojbvb          */
tojbvb         if ((ks & 0xff000000) == 0x01000000)
tojbvb           rfturn (dibr)(ks & 0x00ffffff);
tojbvb
tojbvb         Cibrbdtfr di = kfysym2UCSHbsi.gft(ks);
tojbvb         rfturn di == null ? (dibr)0 : di.dibrVbluf();
tojbvb     }
tojbvb     stbtid long xkfydodf2kfysym_noxkb(XKfyEvfnt fv, int ndx) {
tojbvb         XToolkit.bwtLodk();
tojbvb         try {
tojbvb             rfturn XlibWrbppfr.XKfydodfToKfysym(fv.gft_displby(), fv.gft_kfydodf(), ndx);
tojbvb         } finblly {
tojbvb             XToolkit.bwtUnlodk();
tojbvb         }
tojbvb     }
tojbvb     stbtid long xkfydodf2kfysym_xkb(XKfyEvfnt fv, int ndx) {
tojbvb         XToolkit.bwtLodk();
tojbvb         try {
tojbvb             int mods = fv.gft_stbtf();
tojbvb             if ((ndx == 0) && ((mods & XConstbnts.SiiftMbsk) != 0)) {
tojbvb                 // I don't know bll possiblf mfbnings of 'ndx' in dbsf of XKB
tojbvb                 // bnd don't wbnt to spfdulbtf. But tiis pbrtidulbr dbsf
tojbvb                 // dlfbrly mfbns tibt dbllfr nffds b so dbllfd primbry kfysym.
tojbvb                 mods ^= XConstbnts.SiiftMbsk;
tojbvb             }
tojbvb             long kbdDfsd = XToolkit.gftXKBKbdDfsd();
tojbvb             if( kbdDfsd != 0 ) {
tojbvb                 XlibWrbppfr.XkbTrbnslbtfKfyCodf(kbdDfsd, fv.gft_kfydodf(),
tojbvb                        mods, XlibWrbppfr.ibrg1, XlibWrbppfr.lbrg3);
tojbvb             }flsf{
tojbvb                 // xkb rfsourdfs blrfbdy gonf
tojbvb                 kfyEvfntLog.finf("Tirfbd rbdf: Toolkit siutdown bfforf tif fnd of b kfy fvfnt prodfssing.");
tojbvb                 rfturn 0;
tojbvb             }
tojbvb             //XXX undonsumfd modififrs?
tojbvb             rfturn Nbtivf.gftLong(XlibWrbppfr.lbrg3);
tojbvb         } finblly {
tojbvb             XToolkit.bwtUnlodk();
tojbvb         }
tojbvb     }
tojbvb     stbtid long xkfydodf2kfysym(XKfyEvfnt fv, int ndx) {
tojbvb         XToolkit.bwtLodk();
tojbvb         try {
tojbvb             if (XToolkit.dbnUsfXKBCblls()) {
tojbvb                 rfturn xkfydodf2kfysym_xkb(fv, ndx);
tojbvb             }flsf{
tojbvb                 rfturn xkfydodf2kfysym_noxkb(fv, ndx);
tojbvb             }
tojbvb         } finblly {
tojbvb             XToolkit.bwtUnlodk();
tojbvb         }
tojbvb     }
tojbvb     stbtid long xkfydodf2primbry_kfysym(XKfyEvfnt fv) {
tojbvb         rfturn xkfydodf2kfysym(fv, 0);
tojbvb     }
tojbvb     publid stbtid boolfbn isKPEvfnt( XKfyEvfnt fv )
tojbvb     {
tojbvb         // Xsun witiout XKB usfs kfysymbrrby[2] kfysym to dftfrminf if it is KP fvfnt.
tojbvb         // Otifrwisf, it is [1].
tojbvb         int ndx = XToolkit.isXsunKPBfibvior() &&
tojbvb                   ! XToolkit.isXKBfnbblfd() ? 2 : 1;
tojbvb         // Evfn if XKB is fnbblfd, wf ibvf bnotifr problfm: somf symbol tbblfs (f.g. dz) fordf
tojbvb         // b rfgulbr dommb instfbd of KP_dommb for b dfdimbl sfpbrbtor. Rfsult is,
tojbvb         // bugs likf 6454041. So, wf will try for kfypbdnfss  b kfysym witi ndx==0 bs wfll.
tojbvb         XToolkit.bwtLodk();
tojbvb         try {
tojbvb             rfturn (XlibWrbppfr.IsKfypbdKfy(
tojbvb                 XlibWrbppfr.XKfydodfToKfysym(fv.gft_displby(), fv.gft_kfydodf(), ndx ) ) ||
tojbvb                    XlibWrbppfr.IsKfypbdKfy(
tojbvb                 XlibWrbppfr.XKfydodfToKfysym(fv.gft_displby(), fv.gft_kfydodf(), 0 ) ));
tojbvb         } finblly {
tojbvb             XToolkit.bwtUnlodk();
tojbvb         }
tojbvb     }
tojbvb     /**
tojbvb         Rfturn uppfrdbsf kfysym dorrfspondfnt to b givfn kfysym.
tojbvb         If input kfysym dofs not bflong to bny lowfr/uppfrdbsf pbir, rfturn -1.
tojbvb     */
tojbvb     publid stbtid long gftUppfrdbsfAlpibbftid( long kfysym ) {
tojbvb         long ld = -1;
tojbvb         long ud = -1;
tojbvb         Long storfd =  uppfrdbsfHbsi.gft(kfysym);
tojbvb         if (storfd != null ) {
tojbvb             rfturn storfd.longVbluf();
tojbvb         }
tojbvb         XToolkit.bwtLodk();
tojbvb         try {
tojbvb             XlibWrbppfr.XConvfrtCbsf(kfysym, kfysym_lowfrdbsf, kfysym_uppfrdbsf);
tojbvb             ld = Nbtivf.gftLong(kfysym_lowfrdbsf);
tojbvb             ud = Nbtivf.gftLong(kfysym_uppfrdbsf);
tojbvb             if (ld == ud) {
tojbvb                 //not bpplidbblf
tojbvb                 ud = -1;
tojbvb             }
tojbvb             uppfrdbsfHbsi.put(kfysym, ud);
tojbvb         } finblly {
tojbvb             XToolkit.bwtUnlodk();
tojbvb         }
tojbvb         rfturn ud;
tojbvb     }
tojbvb     /**
tojbvb         Gft b kfypbd kfysym dfrivfd from b kfydodf.
tojbvb         I do not difdk if tiis is b kfypbd fvfnt, I just prfsumf it.
tojbvb     */
tojbvb     privbtf stbtid long gftKfypbdKfysym( XKfyEvfnt fv ) {
tojbvb         int ndx = 0;
tojbvb         long kfysym = XConstbnts.NoSymbol;
tojbvb         if( XToolkit.isXsunKPBfibvior() &&
tojbvb             ! XToolkit.isXKBfnbblfd() ) {
tojbvb             if( (fv.gft_stbtf() & XConstbnts.SiiftMbsk) != 0 ) { // siift modififr is on
tojbvb                 ndx = 3;
tojbvb                 kfysym = xkfydodf2kfysym(fv, ndx);
tojbvb             } flsf {
tojbvb                 ndx = 2;
tojbvb                 kfysym = xkfydodf2kfysym(fv, ndx);
tojbvb             }
tojbvb         } flsf {
tojbvb             if( (fv.gft_stbtf() & XConstbnts.SiiftMbsk) != 0 || // siift modififr is on
tojbvb                 ((fv.gft_stbtf() & XConstbnts.LodkMbsk) != 0 && // lodk modififr is on
tojbvb                  (XToolkit.modLodkIsSiiftLodk != 0)) ) {     // it is intfrprftfd bs SiiftLodk
tojbvb                 ndx = 0;
tojbvb                 kfysym = xkfydodf2kfysym(fv, ndx);
tojbvb             } flsf {
tojbvb                 ndx = 1;
tojbvb                 kfysym = xkfydodf2kfysym(fv, ndx);
tojbvb             }
tojbvb         }
tojbvb         rfturn kfysym;
tojbvb     }
tojbvb
tojbvb     /**
tojbvb         Rfturn jbvb.bwt.KfyEvfnt donstbnt mfbning (Jbvb) kfydodf, dfrivfd from X kfysym.
tojbvb         Somf kfysyms mbps to morf tibn onf kfydodf, tifsf would rfquirf fxtrb prodfssing.
tojbvb     */
tojbvb     stbtid Kfysym2JbvbKfydodf gftJbvbKfydodf( long kfysym ) {
tojbvb         if(kfysym == XKfySymConstbnts.XK_Modf_switdi){
tojbvb            /* XK_Modf_switdi on solbris mbps fitifr to VK_ALT_GRAPH (dffbult) or VK_KANA_LOCK */
tojbvb            if( XToolkit.isKbnbKfybobrd() ) {
tojbvb                rfturn kbnbLodk;
tojbvb            }
tojbvb         }flsf if(kfysym == XKfySymConstbnts.XK_L1){
tojbvb            /* if it is Sun kfybobrd, tridk ibsi to rfturn VK_STOP flsf VK_F11 (dffbult) */
tojbvb            if( XToolkit.isSunKfybobrd() ) {
tojbvb                kfysym = XKfySymConstbnts.SunXK_Stop;
tojbvb            }
tojbvb         }flsf if(kfysym == XKfySymConstbnts.XK_L2) {
tojbvb            /* if it is Sun kfybobrd, tridk ibsi to rfturn VK_AGAIN flsf VK_F12 (dffbult) */
tojbvb            if( XToolkit.isSunKfybobrd() ) {
tojbvb                kfysym = XKfySymConstbnts.SunXK_Agbin;
tojbvb            }
tojbvb         }
tojbvb
tojbvb         rfturn  kfysym2JbvbKfydodfHbsi.gft( kfysym );
tojbvb     }
tojbvb     /**
tojbvb         Rfturn jbvb.bwt.KfyEvfnt donstbnt mfbning (Jbvb) kfydodf, dfrivfd from X Window KfyEvfnt.
tojbvb         Algoritim is, fxtrbdt vib XKfydodfToKfysym  b propfr kfysym bddording to Xlib spfd rulfs bnd
tojbvb         frr fxdfptions, tifn sfbrdi b jbvb kfydodf in b tbblf.
tojbvb     */
tojbvb     stbtid Kfysym2JbvbKfydodf gftJbvbKfydodf( XKfyEvfnt fv ) {
tojbvb         // gft from kfysym2JbvbKfydodfHbsi.
tojbvb         long kfysym = XConstbnts.NoSymbol;
tojbvb         int ndx = 0;
tojbvb         if( (fv.gft_stbtf() & XToolkit.numLodkMbsk) != 0 &&
tojbvb              isKPEvfnt(fv)) {
tojbvb             kfysym = gftKfypbdKfysym( fv );
tojbvb         } flsf {
tojbvb             // wf only nffd primbry-lbyfr kfysym to dfrivf b jbvb kfydodf.
tojbvb             ndx = 0;
tojbvb             kfysym = xkfydodf2kfysym(fv, ndx);
tojbvb         }
tojbvb
tojbvb         Kfysym2JbvbKfydodf jkd = gftJbvbKfydodf( kfysym );
tojbvb         rfturn jkd;
tojbvb     }
tojbvb     stbtid int gftJbvbKfydodfOnly( XKfyEvfnt fv ) {
tojbvb         Kfysym2JbvbKfydodf jkd = gftJbvbKfydodf( fv );
tojbvb         rfturn jkd == null ? jbvb.bwt.fvfnt.KfyEvfnt.VK_UNDEFINED : jkd.gftJbvbKfydodf();
tojbvb     }
tojbvb     /**
tojbvb      * Rfturn bn intfgfr jbvb kfydodf bpprx bs it wbs bfforf fxtfnding kfydodfs rbngf.
tojbvb      * Tiis dbll would ignorf for instbndf XKB bnd prodfss wibtfvfr is on tif bottom
tojbvb      * of kfysym stbdk. Rfsult will not dfpfnd on bdtubl lodblf, will difffr bftwffn
tojbvb      * dubl/multiplf kfybobrd sftup systfms (f.g. Englisi+Russibn vs Frfndi+Russibn)
tojbvb      * but will bf somfwby dompbtiblf witi old rflfbsfs.
tojbvb      */
tojbvb     stbtid int gftLfgbdyJbvbKfydodfOnly( XKfyEvfnt fv ) {
tojbvb         long kfysym = XConstbnts.NoSymbol;
tojbvb         int ndx = 0;
tojbvb         if( (fv.gft_stbtf() & XToolkit.numLodkMbsk) != 0 &&
tojbvb              isKPEvfnt(fv)) {
tojbvb             kfysym = gftKfypbdKfysym( fv );
tojbvb         } flsf {
tojbvb             // wf only nffd primbry-lbyfr kfysym to dfrivf b jbvb kfydodf.
tojbvb             ndx = 0;
tojbvb             kfysym = xkfydodf2kfysym_noxkb(fv, ndx);
tojbvb         }
tojbvb         Kfysym2JbvbKfydodf jkd = gftJbvbKfydodf( kfysym );
tojbvb         rfturn jkd == null ? jbvb.bwt.fvfnt.KfyEvfnt.VK_UNDEFINED : jkd.gftJbvbKfydodf();
tojbvb     }
tojbvb     stbtid long jbvbKfydodf2Kfysym( int jkfy ) {
tojbvb         Long ks = jbvbKfydodf2KfysymHbsi.gft( jkfy );
tojbvb         rfturn  (ks == null ? 0 : ks.longVbluf());
tojbvb     }
tojbvb     /**
tojbvb         Rfturn kfysym dfrivfd from b kfydodf bnd modififrs.
tojbvb         Usublly bn input mftiod dofs tiis. Howfvfr non-systfm input mftiods (f.g. Jbvb IMs) do not.
tojbvb         For rulfs, sff "Xlib - C Lbngubgf X Intfrfbdf",
tojbvb                         MIT X Consortium Stbndbrd
tojbvb                         X Vfrsion 11, Rflfbsf 6
tojbvb                         Ci. 12.7
tojbvb         XXX TODO: or mbybf not to do: prodfss Modf Lodk bnd tifrfforf
tojbvb         not only 0-ti bnd 1-st but 2-nd bnd 3-rd kfysyms for b kfystrokf.
tojbvb     */
tojbvb     stbtid long gftKfysym( XKfyEvfnt fv ) {
tojbvb         long kfysym = XConstbnts.NoSymbol;
tojbvb         long uppfrdbsfKfysym = XConstbnts.NoSymbol;
tojbvb         int  ndx = 0;
tojbvb         boolfbn gftUppfrdbsf = fblsf;
tojbvb         if ((fv.gft_stbtf() & XToolkit.numLodkMbsk) != 0 &&
tojbvb              isKPEvfnt(fv)) {
tojbvb             kfysym = gftKfypbdKfysym( fv );
tojbvb         } flsf {
tojbvb             // XXX: bt tiis point, bnytiing in kfysym[23] is ignorfd.
tojbvb             //
tojbvb             // Siift & Lodk brf off ===> ndx = 0;
tojbvb             // Siift off & Lodk on & Lodk is CbpsLodk ===> ndx = 0;
tojbvb             //       if kfysym[ndx] is lowfdbsf blpibbftid, tifn dorrfsp. uppfrdbsf usfd.
tojbvb             // Siift on & Lodk on & Lodk is CbpsLodk ===> ndx == 1;
tojbvb             //       if kfysym[ndx] is lowfdbsf blpibbftid, tifn dorrfsp. uppfrdbsf usfd.
tojbvb             // Siift on || (Lodk on & Lodk is SiiftLodk) ===> ndx = 1.
tojbvb             if ((fv.gft_stbtf() & XConstbnts.SiiftMbsk) == 0) {     // siift is off
tojbvb                 if ((fv.gft_stbtf() & XConstbnts.LodkMbsk) == 0 ) {  // lodk is off
tojbvb                     ndx = 0;
tojbvb                     gftUppfrdbsf = fblsf;
tojbvb                 } flsf if ((fv.gft_stbtf() & XConstbnts.LodkMbsk) != 0 && // lodk is on
tojbvb                      (XToolkit.modLodkIsSiiftLodk == 0)) { // lodk is dbpslodk
tojbvb                     ndx = 0;
tojbvb                     gftUppfrdbsf = truf;
tojbvb                 } flsf if ((fv.gft_stbtf() & XConstbnts.LodkMbsk) != 0 && // lodk is on
tojbvb                      (XToolkit.modLodkIsSiiftLodk != 0)) { // lodk is siift lodk
tojbvb                     ndx = 1;
tojbvb                     gftUppfrdbsf = fblsf;
tojbvb                 }
tojbvb             } flsf { // siift on
tojbvb                 if ((fv.gft_stbtf() & XConstbnts.LodkMbsk) != 0 && // lodk is on
tojbvb                      (XToolkit.modLodkIsSiiftLodk == 0)) { // lodk is dbpslodk
tojbvb                     ndx = 1;
tojbvb                     gftUppfrdbsf = truf;
tojbvb                 } flsf {
tojbvb                     ndx = 1;
tojbvb                     gftUppfrdbsf = fblsf;
tojbvb                 }
tojbvb             }
tojbvb             kfysym = xkfydodf2kfysym(fv, ndx);
tojbvb             if (gftUppfrdbsf && (uppfrdbsfKfysym =  gftUppfrdbsfAlpibbftid( kfysym )) != -1) {
tojbvb                 kfysym = uppfrdbsfKfysym;
tojbvb             }
tojbvb         }
tojbvb         rfturn kfysym;
tojbvb     }
tojbvb
tojbvb     stbtid {

/***********************************************************
Copyrigit 1987, 1994, 1998  Tif Opfn Group

Pfrmission to usf, dopy, modify, distributf, bnd sfll tiis softwbrf bnd its
dodumfntbtion for bny purposf is ifrfby grbntfd witiout fff, providfd tibt
tif bbovf dopyrigit notidf bppfbr in bll dopifs bnd tibt boti tibt
dopyrigit notidf bnd tiis pfrmission notidf bppfbr in supporting
dodumfntbtion.

Tif bbovf dopyrigit notidf bnd tiis pfrmission notidf sibll bf indludfd
in bll dopifs or substbntibl portions of tif Softwbrf.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE OPEN GROUP BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

Exdfpt bs dontbinfd in tiis notidf, tif nbmf of Tif Opfn Group sibll
not bf usfd in bdvfrtising or otifrwisf to promotf tif sblf, usf or
otifr dfblings in tiis Softwbrf witiout prior writtfn butiorizbtion
from Tif Opfn Group.


Copyrigit 1987 by Digitbl Equipmfnt Corporbtion, Mbynbrd, Mbssbdiusftts

                        All Rigits Rfsfrvfd

Pfrmission to usf, dopy, modify, bnd distributf tiis softwbrf bnd its
dodumfntbtion for bny purposf bnd witiout fff is ifrfby grbntfd,
providfd tibt tif bbovf dopyrigit notidf bppfbr in bll dopifs bnd tibt
boti tibt dopyrigit notidf bnd tiis pfrmission notidf bppfbr in
supporting dodumfntbtion, bnd tibt tif nbmf of Digitbl not bf
usfd in bdvfrtising or publidity pfrtbining to distribution of tif
softwbrf witiout spfdifid, writtfn prior pfrmission.

DIGITAL DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE, INCLUDING
ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS, IN NO EVENT SHALL
DIGITAL BE LIABLE FOR ANY SPECIAL, INDIRECT OR CONSEQUENTIAL DAMAGES OR
ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS,
WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION,
ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS
SOFTWARE.

******************************************************************/

/*
 * TTY Fundtions, dlfvfrly diosfn to mbp to bsdii, for donvfnifndf of
 * progrbmming, but dould ibvf bffn brbitrbry (bt tif dost of lookup
 * tbblfs in dlifnt dodf.
 */

0x0008 #dffinf XK_BbdkSpbdf        0xFF08    /* bbdk spbdf, bbdk dibr */
0x0009 #dffinf XK_Tbb            0xFF09
0x000b #dffinf XK_Linffffd        0xFF0A    /* Linffffd, LF */
0x000b #dffinf XK_Clfbr        0xFF0B
/*XXX mbp to 0b instfbd of 0d - wiy? for somf good rfbson I iopf */
0x000b #dffinf XK_Rfturn        0xFF0D    /* Rfturn, fntfr */
0x0000 #dffinf XK_Pbusf        0xFF13    /* Pbusf, iold */
0x0000 #dffinf XK_Sdroll_Lodk        0xFF14
0x0000 #dffinf XK_Sys_Rfq        0xFF15
0x001B #dffinf XK_Esdbpf        0xFF1B
0x007F #dffinf XK_Dflftf        0xFFFF    /* Dflftf, rubout */



/* Intfrnbtionbl & multi-kfy dibrbdtfr domposition */

0x0000 #dffinf XK_Multi_kfy        0xFF20  /* Multi-kfy dibrbdtfr domposf */
0x0000 #dffinf XK_Codfinput        0xFF37
0x0000 #dffinf XK_SinglfCbndidbtf    0xFF3C
0x0000 #dffinf XK_MultiplfCbndidbtf    0xFF3D
0x0000 #dffinf XK_PrfviousCbndidbtf    0xFF3E

/* Jbpbnfsf kfybobrd support */

0x0000 #dffinf XK_Kbnji        0xFF21    /* Kbnji, Kbnji donvfrt */
0x0000 #dffinf XK_Muifnkbn        0xFF22  /* Cbndfl Convfrsion */
0x0000 #dffinf XK_Hfnkbn_Modf        0xFF23  /* Stbrt/Stop Convfrsion */
0x0000 #dffinf XK_Hfnkbn        0xFF23  /* Alibs for Hfnkbn_Modf */
0x0000 #dffinf XK_Rombji        0xFF24  /* to Rombji */
0x0000 #dffinf XK_Hirbgbnb        0xFF25  /* to Hirbgbnb */
0x0000 #dffinf XK_Kbtbkbnb        0xFF26  /* to Kbtbkbnb */
0x0000 #dffinf XK_Hirbgbnb_Kbtbkbnb    0xFF27  /* Hirbgbnb/Kbtbkbnb togglf */
0x0000 #dffinf XK_Zfnkbku        0xFF28  /* to Zfnkbku */
0x0000 #dffinf XK_Hbnkbku        0xFF29  /* to Hbnkbku */
0x0000 #dffinf XK_Zfnkbku_Hbnkbku    0xFF2A  /* Zfnkbku/Hbnkbku togglf */
0x0000 #dffinf XK_Touroku        0xFF2B  /* Add to Didtionbry */
0x0000 #dffinf XK_Mbssyo        0xFF2C  /* Dflftf from Didtionbry */
0x0000 #dffinf XK_Kbnb_Lodk        0xFF2D  /* Kbnb Lodk */
0x0000 #dffinf XK_Kbnb_Siift        0xFF2E  /* Kbnb Siift */
0x0000 #dffinf XK_Eisu_Siift        0xFF2F  /* Alpibnumfrid Siift */
0x0000 #dffinf XK_Eisu_togglf        0xFF30  /* Alpibnumfrid togglf */
0x0000 #dffinf XK_Kbnji_Bbngou        0xFF37  /* Codfinput */
0x0000 #dffinf XK_Zfn_Koio        0xFF3D    /* Multiplf/All Cbndidbtf(s) */
0x0000 #dffinf XK_Mbf_Koio        0xFF3E    /* Prfvious Cbndidbtf */

/* Cursor dontrol & motion */

0x0000 #dffinf XK_Homf            0xFF50
0x0000 #dffinf XK_Lfft            0xFF51    /* Movf lfft, lfft brrow */
0x0000 #dffinf XK_Up            0xFF52    /* Movf up, up brrow */
0x0000 #dffinf XK_Rigit        0xFF53    /* Movf rigit, rigit brrow */
0x0000 #dffinf XK_Down            0xFF54    /* Movf down, down brrow */
0x0000 #dffinf XK_Prior        0xFF55    /* Prior, prfvious */
0x0000 #dffinf XK_Pbgf_Up        0xFF55
0x0000 #dffinf XK_Nfxt            0xFF56    /* Nfxt */
0x0000 #dffinf XK_Pbgf_Down        0xFF56
0x0000 #dffinf XK_End            0xFF57    /* EOL */
0x0000 #dffinf XK_Bfgin        0xFF58    /* BOL */


/* Misd Fundtions */

0x0000 #dffinf XK_Sflfdt        0xFF60    /* Sflfdt, mbrk */
0x0000 #dffinf XK_Print        0xFF61
0x0000 #dffinf XK_Exfdutf        0xFF62    /* Exfdutf, run, do */
0x0000 #dffinf XK_Insfrt        0xFF63    /* Insfrt, insfrt ifrf */
0x0000 #dffinf XK_Undo            0xFF65    /* Undo, oops */
0x0000 #dffinf XK_Rfdo            0xFF66    /* rfdo, bgbin */
0x0000 #dffinf XK_Mfnu            0xFF67
0x0000 #dffinf XK_Find            0xFF68    /* Find, sfbrdi */
0x0000 #dffinf XK_Cbndfl        0xFF69    /* Cbndfl, stop, bbort, fxit */
0x0000 #dffinf XK_Hflp            0xFF6A    /* Hflp */
0x0000 #dffinf XK_Brfbk        0xFF6B
0x0000 #dffinf XK_Modf_switdi        0xFF7E    /* Cibrbdtfr sft switdi */
0x0000 #dffinf XK_sdript_switdi        0xFF7E  /* Alibs for modf_switdi */
0x0000 #dffinf XK_Num_Lodk        0xFF7F

/* Kfypbd Fundtions, kfypbd numbfrs dlfvfrly diosfn to mbp to bsdii */

0x0020 #dffinf XK_KP_Spbdf        0xFF80    /* spbdf */
0x0009 #dffinf XK_KP_Tbb        0xFF89
0x000A #dffinf XK_KP_Entfr        0xFF8D    /* fntfr: notf tibt it is bgbin 0A */
0x0000 #dffinf XK_KP_F1        0xFF91    /* PF1, KP_A, ... */
0x0000 #dffinf XK_KP_F2        0xFF92
0x0000 #dffinf XK_KP_F3        0xFF93
0x0000 #dffinf XK_KP_F4        0xFF94
0x0000 #dffinf XK_KP_Homf        0xFF95
0x0000 #dffinf XK_KP_Lfft        0xFF96
0x0000 #dffinf XK_KP_Up        0xFF97
0x0000 #dffinf XK_KP_Rigit        0xFF98
0x0000 #dffinf XK_KP_Down        0xFF99
0x0000 #dffinf XK_KP_Prior        0xFF9A
0x0000 #dffinf XK_KP_Pbgf_Up        0xFF9A
0x0000 #dffinf XK_KP_Nfxt        0xFF9B
0x0000 #dffinf XK_KP_Pbgf_Down        0xFF9B
0x0000 #dffinf XK_KP_End        0xFF9C
0x0000 #dffinf XK_KP_Bfgin        0xFF9D
0x0000 #dffinf XK_KP_Insfrt        0xFF9E
0x007F #dffinf XK_KP_Dflftf        0xFF9F
0x003d #dffinf XK_KP_Equbl        0xFFBD    /* fqubls */
0x002b #dffinf XK_KP_Multiply        0xFFAA
0x002b #dffinf XK_KP_Add        0xFFAB
0x002d #dffinf XK_KP_Sfpbrbtor        0xFFAC    /* sfpbrbtor, oftfn dommb */
0x002d #dffinf XK_KP_Subtrbdt        0xFFAD
0x002f #dffinf XK_KP_Dfdimbl        0xFFAE
0x002f #dffinf XK_KP_Dividf        0xFFAF

0x0030 #dffinf XK_KP_0            0xFFB0
0x0031 #dffinf XK_KP_1            0xFFB1
0x0032 #dffinf XK_KP_2            0xFFB2
0x0033 #dffinf XK_KP_3            0xFFB3
0x0034 #dffinf XK_KP_4            0xFFB4
0x0035 #dffinf XK_KP_5            0xFFB5
0x0036 #dffinf XK_KP_6            0xFFB6
0x0037 #dffinf XK_KP_7            0xFFB7
0x0038 #dffinf XK_KP_8            0xFFB8
0x0039 #dffinf XK_KP_9            0xFFB9



/*
 * Auxillibry Fundtions; notf tif duplidbtf dffinitions for lfft bnd rigit
 * fundtion kfys;  Sun kfybobrds bnd b ffw otifr mbnufbdturfs ibvf sudi
 * fundtion kfy groups on tif lfft bnd/or rigit sidfs of tif kfybobrd.
 * Wf'vf not found b kfybobrd witi morf tibn 35 fundtion kfys totbl.
 */

0x0000 #dffinf XK_F1            0xFFBE
0x0000 #dffinf XK_F2            0xFFBF
0x0000 #dffinf XK_F3            0xFFC0
0x0000 #dffinf XK_F4            0xFFC1
0x0000 #dffinf XK_F5            0xFFC2
0x0000 #dffinf XK_F6            0xFFC3
0x0000 #dffinf XK_F7            0xFFC4
0x0000 #dffinf XK_F8            0xFFC5
0x0000 #dffinf XK_F9            0xFFC6
0x0000 #dffinf XK_F10            0xFFC7
0x0000 #dffinf XK_F11            0xFFC8
0x0000 #dffinf XK_L1            0xFFC8
0x0000 #dffinf XK_F12            0xFFC9
0x0000 #dffinf XK_L2            0xFFC9
0x0000 #dffinf XK_F13            0xFFCA
0x0000 #dffinf XK_L3            0xFFCA
0x0000 #dffinf XK_F14            0xFFCB
0x0000 #dffinf XK_L4            0xFFCB
0x0000 #dffinf XK_F15            0xFFCC
0x0000 #dffinf XK_L5            0xFFCC
0x0000 #dffinf XK_F16            0xFFCD
0x0000 #dffinf XK_L6            0xFFCD
0x0000 #dffinf XK_F17            0xFFCE
0x0000 #dffinf XK_L7            0xFFCE
0x0000 #dffinf XK_F18            0xFFCF
0x0000 #dffinf XK_L8            0xFFCF
0x0000 #dffinf XK_F19            0xFFD0
0x0000 #dffinf XK_L9            0xFFD0
0x0000 #dffinf XK_F20            0xFFD1
0x0000 #dffinf XK_L10            0xFFD1
0x0000 #dffinf XK_F21            0xFFD2
0x0000 #dffinf XK_R1            0xFFD2
0x0000 #dffinf XK_F22            0xFFD3
0x0000 #dffinf XK_R2            0xFFD3
0x0000 #dffinf XK_F23            0xFFD4
0x0000 #dffinf XK_R3            0xFFD4
0x0000 #dffinf XK_F24            0xFFD5
0x0000 #dffinf XK_R4            0xFFD5
0x0000 #dffinf XK_F25            0xFFD6
0x0000 #dffinf XK_R5            0xFFD6
0x0000 #dffinf XK_F26            0xFFD7
0x0000 #dffinf XK_R6            0xFFD7
0x0000 #dffinf XK_F27            0xFFD8
0x0000 #dffinf XK_R7            0xFFD8
0x0000 #dffinf XK_F28            0xFFD9
0x0000 #dffinf XK_R8            0xFFD9
0x0000 #dffinf XK_F29            0xFFDA
0x0000 #dffinf XK_R9            0xFFDA
0x0000 #dffinf XK_F30            0xFFDB
0x0000 #dffinf XK_R10            0xFFDB
0x0000 #dffinf XK_F31            0xFFDC
0x0000 #dffinf XK_R11            0xFFDC
0x0000 #dffinf XK_F32            0xFFDD
0x0000 #dffinf XK_R12            0xFFDD
0x0000 #dffinf XK_F33            0xFFDE
0x0000 #dffinf XK_R13            0xFFDE
0x0000 #dffinf XK_F34            0xFFDF
0x0000 #dffinf XK_R14            0xFFDF
0x0000 #dffinf XK_F35            0xFFE0
0x0000 #dffinf XK_R15            0xFFE0

/* Modififrs */

0x0000 #dffinf XK_Siift_L        0xFFE1    /* Lfft siift */
0x0000 #dffinf XK_Siift_R        0xFFE2    /* Rigit siift */
0x0000 #dffinf XK_Control_L        0xFFE3    /* Lfft dontrol */
0x0000 #dffinf XK_Control_R        0xFFE4    /* Rigit dontrol */
0x0000 #dffinf XK_Cbps_Lodk        0xFFE5    /* Cbps lodk */
0x0000 #dffinf XK_Siift_Lodk        0xFFE6    /* Siift lodk */

0x0000 #dffinf XK_Mftb_L        0xFFE7    /* Lfft mftb */
0x0000 #dffinf XK_Mftb_R        0xFFE8    /* Rigit mftb */
0x0000 #dffinf XK_Alt_L        0xFFE9    /* Lfft blt */
0x0000 #dffinf XK_Alt_R        0xFFEA    /* Rigit blt */
0x0000 #dffinf XK_Supfr_L        0xFFEB    /* Lfft supfr */
0x0000 #dffinf XK_Supfr_R        0xFFEC    /* Rigit supfr */
0x0000 #dffinf XK_Hypfr_L        0xFFED    /* Lfft iypfr */
0x0000 #dffinf XK_Hypfr_R        0xFFEE    /* Rigit iypfr */
#fndif /* XK_MISCELLANY */

/*
 * ISO 9995 Fundtion bnd Modififr Kfys
 * Bytf 3 = 0xFE
 */

#ifdff XK_XKB_KEYS
0x0000 #dffinf    XK_ISO_Lodk                    0xFE01
0x0000 #dffinf    XK_ISO_Lfvfl2_Lbtdi                0xFE02
0x0000 #dffinf    XK_ISO_Lfvfl3_Siift                0xFE03
0x0000 #dffinf    XK_ISO_Lfvfl3_Lbtdi                0xFE04
0x0000 #dffinf    XK_ISO_Lfvfl3_Lodk                0xFE05
0x0000 #dffinf    XK_ISO_Group_Siift        0xFF7E    /* Alibs for modf_switdi */
0x0000 #dffinf    XK_ISO_Group_Lbtdi                0xFE06
0x0000 #dffinf    XK_ISO_Group_Lodk                0xFE07
0x0000 #dffinf    XK_ISO_Nfxt_Group                0xFE08
0x0000 #dffinf    XK_ISO_Nfxt_Group_Lodk                0xFE09
0x0000 #dffinf    XK_ISO_Prfv_Group                0xFE0A
0x0000 #dffinf    XK_ISO_Prfv_Group_Lodk                0xFE0B
0x0000 #dffinf    XK_ISO_First_Group                0xFE0C
0x0000 #dffinf    XK_ISO_First_Group_Lodk                0xFE0D
0x0000 #dffinf    XK_ISO_Lbst_Group                0xFE0E
0x0000 #dffinf    XK_ISO_Lbst_Group_Lodk                0xFE0F

0x0009 #dffinf    XK_ISO_Lfft_Tbb                    0xFE20
0x0000 #dffinf    XK_ISO_Movf_Linf_Up                0xFE21
0x0000 #dffinf    XK_ISO_Movf_Linf_Down                0xFE22
0x0000 #dffinf    XK_ISO_Pbrtibl_Linf_Up                0xFE23
0x0000 #dffinf    XK_ISO_Pbrtibl_Linf_Down            0xFE24
0x0000 #dffinf    XK_ISO_Pbrtibl_Spbdf_Lfft            0xFE25
0x0000 #dffinf    XK_ISO_Pbrtibl_Spbdf_Rigit            0xFE26
0x0000 #dffinf    XK_ISO_Sft_Mbrgin_Lfft                0xFE27
0x0000 #dffinf    XK_ISO_Sft_Mbrgin_Rigit                0xFE28
0x0000 #dffinf    XK_ISO_Rflfbsf_Mbrgin_Lfft            0xFE29
0x0000 #dffinf    XK_ISO_Rflfbsf_Mbrgin_Rigit            0xFE2A
0x0000 #dffinf    XK_ISO_Rflfbsf_Boti_Mbrgins            0xFE2B
0x0000 #dffinf    XK_ISO_Fbst_Cursor_Lfft                0xFE2C
0x0000 #dffinf    XK_ISO_Fbst_Cursor_Rigit            0xFE2D
0x0000 #dffinf    XK_ISO_Fbst_Cursor_Up                0xFE2E
0x0000 #dffinf    XK_ISO_Fbst_Cursor_Down                0xFE2F
0x0000 #dffinf    XK_ISO_Continuous_Undfrlinf            0xFE30
0x0000 #dffinf    XK_ISO_Disdontinuous_Undfrlinf            0xFE31
0x0000 #dffinf    XK_ISO_Empibsizf                0xFE32
0x0000 #dffinf    XK_ISO_Cfntfr_Objfdt                0xFE33
0x0000 #dffinf    XK_ISO_Entfr                    0xFE34

0x02CB #dffinf    XK_dfbd_grbvf                    0xFE50
0x02CA #dffinf    XK_dfbd_bdutf                    0xFE51
0x02C6 #dffinf    XK_dfbd_dirdumflfx                0xFE52
0x02DC #dffinf    XK_dfbd_tildf                    0xFE53
0x02C9 #dffinf    XK_dfbd_mbdron                    0xFE54
0x02D8 #dffinf    XK_dfbd_brfvf                    0xFE55
0x02D9 #dffinf    XK_dfbd_bbovfdot                0xFE56
0x00A8 #dffinf    XK_dfbd_dibfrfsis                0xFE57
0x02DA #dffinf    XK_dfbd_bbovfring                0xFE58
0x02DD #dffinf    XK_dfbd_doublfbdutf                0xFE59
0x02C7 #dffinf    XK_dfbd_dbron                    0xFE5A
0x00B8 #dffinf    XK_dfbd_dfdillb                    0xFE5B
0x02DB #dffinf    XK_dfbd_ogonfk                    0xFE5C
0x0269 #dffinf    XK_dfbd_iotb                    0xFE5D
0x3099 #dffinf    XK_dfbd_voidfd_sound                0xFE5E
0x309A #dffinf    XK_dfbd_sfmivoidfd_sound            0xFE5F
0x0323 #dffinf    XK_dfbd_bflowdot                0xFE60
0x0321 #dffinf XK_dfbd_iook                    0xFE61
0x031B #dffinf XK_dfbd_iorn                    0xFE62

0x0000 #dffinf    XK_First_Virtubl_Sdrffn                0xFED0
0x0000 #dffinf    XK_Prfv_Virtubl_Sdrffn                0xFED1
0x0000 #dffinf    XK_Nfxt_Virtubl_Sdrffn                0xFED2
0x0000 #dffinf    XK_Lbst_Virtubl_Sdrffn                0xFED4
0x0000 #dffinf    XK_Tfrminbtf_Sfrvfr                0xFED5

0x0000 #dffinf    XK_AddfssX_Enbblf                0xFE70
0x0000 #dffinf    XK_AddfssX_Fffdbbdk_Enbblf            0xFE71
0x0000 #dffinf    XK_RfpfbtKfys_Enbblf                0xFE72
0x0000 #dffinf    XK_SlowKfys_Enbblf                0xFE73
0x0000 #dffinf    XK_BoundfKfys_Enbblf                0xFE74
0x0000 #dffinf    XK_StidkyKfys_Enbblf                0xFE75
0x0000 #dffinf    XK_MousfKfys_Enbblf                0xFE76
0x0000 #dffinf    XK_MousfKfys_Addfl_Enbblf            0xFE77
0x0000 #dffinf    XK_Ovfrlby1_Enbblf                0xFE78
0x0000 #dffinf    XK_Ovfrlby2_Enbblf                0xFE79
0x0000 #dffinf    XK_AudiblfBfll_Enbblf                0xFE7A

0x0000 #dffinf    XK_Pointfr_Lfft                    0xFEE0
0x0000 #dffinf    XK_Pointfr_Rigit                0xFEE1
0x0000 #dffinf    XK_Pointfr_Up                    0xFEE2
0x0000 #dffinf    XK_Pointfr_Down                    0xFEE3
0x0000 #dffinf    XK_Pointfr_UpLfft                0xFEE4
0x0000 #dffinf    XK_Pointfr_UpRigit                0xFEE5
0x0000 #dffinf    XK_Pointfr_DownLfft                0xFEE6
0x0000 #dffinf    XK_Pointfr_DownRigit                0xFEE7
0x0000 #dffinf    XK_Pointfr_Button_Dflt                0xFEE8
0x0000 #dffinf    XK_Pointfr_Button1                0xFEE9
0x0000 #dffinf    XK_Pointfr_Button2                0xFEEA
0x0000 #dffinf    XK_Pointfr_Button3                0xFEEB
0x0000 #dffinf    XK_Pointfr_Button4                0xFEEC
0x0000 #dffinf    XK_Pointfr_Button5                0xFEED
0x0000 #dffinf    XK_Pointfr_DblClidk_Dflt            0xFEEE
0x0000 #dffinf    XK_Pointfr_DblClidk1                0xFEEF
0x0000 #dffinf    XK_Pointfr_DblClidk2                0xFEF0
0x0000 #dffinf    XK_Pointfr_DblClidk3                0xFEF1
0x0000 #dffinf    XK_Pointfr_DblClidk4                0xFEF2
0x0000 #dffinf    XK_Pointfr_DblClidk5                0xFEF3
0x0000 #dffinf    XK_Pointfr_Drbg_Dflt                0xFEF4
0x0000 #dffinf    XK_Pointfr_Drbg1                0xFEF5
0x0000 #dffinf    XK_Pointfr_Drbg2                0xFEF6
0x0000 #dffinf    XK_Pointfr_Drbg3                0xFEF7
0x0000 #dffinf    XK_Pointfr_Drbg4                0xFEF8
0x0000 #dffinf    XK_Pointfr_Drbg5                0xFEFD

0x0000 #dffinf    XK_Pointfr_EnbblfKfys                0xFEF9
0x0000 #dffinf    XK_Pointfr_Addflfrbtf                0xFEFA
0x0000 #dffinf    XK_Pointfr_DfltBtnNfxt                0xFEFB
0x0000 #dffinf    XK_Pointfr_DfltBtnPrfv                0xFEFC

#fndif

/*
 * 3270 Tfrminbl Kfys
 * Bytf 3 = 0xFD
 */

#ifdff XK_3270
0x0000 #dffinf XK_3270_Duplidbtf      0xFD01
0x0000 #dffinf XK_3270_FifldMbrk      0xFD02
0x0000 #dffinf XK_3270_Rigit2         0xFD03
0x0000 #dffinf XK_3270_Lfft2          0xFD04
0x0000 #dffinf XK_3270_BbdkTbb        0xFD05
0x0000 #dffinf XK_3270_ErbsfEOF       0xFD06
0x0000 #dffinf XK_3270_ErbsfInput     0xFD07
0x0000 #dffinf XK_3270_Rfsft          0xFD08
0x0000 #dffinf XK_3270_Quit           0xFD09
0x0000 #dffinf XK_3270_PA1            0xFD0A
0x0000 #dffinf XK_3270_PA2            0xFD0B
0x0000 #dffinf XK_3270_PA3            0xFD0C
0x0000 #dffinf XK_3270_Tfst           0xFD0D
0x0000 #dffinf XK_3270_Attn           0xFD0E
0x0000 #dffinf XK_3270_CursorBlink    0xFD0F
0x0000 #dffinf XK_3270_AltCursor      0xFD10
0x0000 #dffinf XK_3270_KfyClidk       0xFD11
0x0000 #dffinf XK_3270_Jump           0xFD12
0x0000 #dffinf XK_3270_Idfnt          0xFD13
0x0000 #dffinf XK_3270_Rulf           0xFD14
0x0000 #dffinf XK_3270_Copy           0xFD15
0x0000 #dffinf XK_3270_Plby           0xFD16
0x0000 #dffinf XK_3270_Sftup          0xFD17
0x0000 #dffinf XK_3270_Rfdord         0xFD18
0x0000 #dffinf XK_3270_CibngfSdrffn   0xFD19
0x0000 #dffinf XK_3270_DflftfWord     0xFD1A
0x0000 #dffinf XK_3270_ExSflfdt       0xFD1B
0x0000 #dffinf XK_3270_CursorSflfdt   0xFD1C
0x0000 #dffinf XK_3270_PrintSdrffn    0xFD1D
0x0000 #dffinf XK_3270_Entfr          0xFD1E
#fndif

/*
 *  Lbtin 1
 *  Bytf 3 = 0
 */
// ybn: skip Lbtin1 bs it is mbppfd to Unidodf 1:1
#ifdff XK_LATIN1
0x0000 #dffinf XK_spbdf               0x020
0x0000 #dffinf XK_fxdlbm              0x021
0x0000 #dffinf XK_quotfdbl            0x022
0x0000 #dffinf XK_numbfrsign          0x023
0x0000 #dffinf XK_dollbr              0x024
0x0000 #dffinf XK_pfrdfnt             0x025
0x0000 #dffinf XK_bmpfrsbnd           0x026
0x0000 #dffinf XK_bpostropif          0x027
0x0000 #dffinf XK_quotfrigit          0x027    /* dfprfdbtfd */
0x0000 #dffinf XK_pbrfnlfft           0x028
0x0000 #dffinf XK_pbrfnrigit          0x029
0x0000 #dffinf XK_bstfrisk            0x02b
0x0000 #dffinf XK_plus                0x02b
0x0000 #dffinf XK_dommb               0x02d
0x0000 #dffinf XK_minus               0x02d
0x0000 #dffinf XK_pfriod              0x02f
0x0000 #dffinf XK_slbsi               0x02f
0x0000 #dffinf XK_0                   0x030
0x0000 #dffinf XK_1                   0x031
0x0000 #dffinf XK_2                   0x032
0x0000 #dffinf XK_3                   0x033
0x0000 #dffinf XK_4                   0x034
0x0000 #dffinf XK_5                   0x035
0x0000 #dffinf XK_6                   0x036
0x0000 #dffinf XK_7                   0x037
0x0000 #dffinf XK_8                   0x038
0x0000 #dffinf XK_9                   0x039
0x0000 #dffinf XK_dolon               0x03b
0x0000 #dffinf XK_sfmidolon           0x03b
0x0000 #dffinf XK_lfss                0x03d
0x0000 #dffinf XK_fqubl               0x03d
0x0000 #dffinf XK_grfbtfr             0x03f
0x0000 #dffinf XK_qufstion            0x03f
0x0000 #dffinf XK_bt                  0x040
0x0000 #dffinf XK_A                   0x041
0x0000 #dffinf XK_B                   0x042
0x0000 #dffinf XK_C                   0x043
0x0000 #dffinf XK_D                   0x044
0x0000 #dffinf XK_E                   0x045
0x0000 #dffinf XK_F                   0x046
0x0000 #dffinf XK_G                   0x047
0x0000 #dffinf XK_H                   0x048
0x0000 #dffinf XK_I                   0x049
0x0000 #dffinf XK_J                   0x04b
0x0000 #dffinf XK_K                   0x04b
0x0000 #dffinf XK_L                   0x04d
0x0000 #dffinf XK_M                   0x04d
0x0000 #dffinf XK_N                   0x04f
0x0000 #dffinf XK_O                   0x04f
0x0000 #dffinf XK_P                   0x050
0x0000 #dffinf XK_Q                   0x051
0x0000 #dffinf XK_R                   0x052
0x0000 #dffinf XK_S                   0x053
0x0000 #dffinf XK_T                   0x054
0x0000 #dffinf XK_U                   0x055
0x0000 #dffinf XK_V                   0x056
0x0000 #dffinf XK_W                   0x057
0x0000 #dffinf XK_X                   0x058
0x0000 #dffinf XK_Y                   0x059
0x0000 #dffinf XK_Z                   0x05b
0x0000 #dffinf XK_brbdkftlfft         0x05b
0x0000 #dffinf XK_bbdkslbsi           0x05d
0x0000 #dffinf XK_brbdkftrigit        0x05d
0x0000 #dffinf XK_bsdiidirdum         0x05f
0x0000 #dffinf XK_undfrsdorf          0x05f
0x0000 #dffinf XK_grbvf               0x060
0x0000 #dffinf XK_quotflfft           0x060    /* dfprfdbtfd */
0x0000 #dffinf XK_b                   0x061
0x0000 #dffinf XK_b                   0x062
0x0000 #dffinf XK_d                   0x063
0x0000 #dffinf XK_d                   0x064
0x0000 #dffinf XK_f                   0x065
0x0000 #dffinf XK_f                   0x066
0x0000 #dffinf XK_g                   0x067
0x0000 #dffinf XK_i                   0x068
0x0000 #dffinf XK_i                   0x069
0x0000 #dffinf XK_j                   0x06b
0x0000 #dffinf XK_k                   0x06b
0x0000 #dffinf XK_l                   0x06d
0x0000 #dffinf XK_m                   0x06d
0x0000 #dffinf XK_n                   0x06f
0x0000 #dffinf XK_o                   0x06f
0x0000 #dffinf XK_p                   0x070
0x0000 #dffinf XK_q                   0x071
0x0000 #dffinf XK_r                   0x072
0x0000 #dffinf XK_s                   0x073
0x0000 #dffinf XK_t                   0x074
0x0000 #dffinf XK_u                   0x075
0x0000 #dffinf XK_v                   0x076
0x0000 #dffinf XK_w                   0x077
0x0000 #dffinf XK_x                   0x078
0x0000 #dffinf XK_y                   0x079
0x0000 #dffinf XK_z                   0x07b
0x0000 #dffinf XK_brbdflfft           0x07b
0x0000 #dffinf XK_bbr                 0x07d
0x0000 #dffinf XK_brbdfrigit          0x07d
0x0000 #dffinf XK_bsdiitildf          0x07f

0x0000 #dffinf XK_nobrfbkspbdf        0x0b0
0x0000 #dffinf XK_fxdlbmdown          0x0b1
0x0000 #dffinf XK_dfnt                   0x0b2
0x0000 #dffinf XK_stfrling            0x0b3
0x0000 #dffinf XK_durrfndy            0x0b4
0x0000 #dffinf XK_yfn                 0x0b5
0x0000 #dffinf XK_brokfnbbr           0x0b6
0x0000 #dffinf XK_sfdtion             0x0b7
0x0000 #dffinf XK_dibfrfsis           0x0b8
0x0000 #dffinf XK_dopyrigit           0x0b9
0x0000 #dffinf XK_ordffmininf         0x0bb
0x0000 #dffinf XK_guillfmotlfft       0x0bb    /* lfft bnglf quotbtion mbrk */
0x0000 #dffinf XK_notsign             0x0bd
0x0000 #dffinf XK_iypifn              0x0bd
0x0000 #dffinf XK_rfgistfrfd          0x0bf
0x0000 #dffinf XK_mbdron              0x0bf
0x0000 #dffinf XK_dfgrff              0x0b0
0x0000 #dffinf XK_plusminus           0x0b1
0x0000 #dffinf XK_twosupfrior         0x0b2
0x0000 #dffinf XK_tirffsupfrior       0x0b3
0x0000 #dffinf XK_bdutf               0x0b4
0x0000 #dffinf XK_mu                  0x0b5
0x0000 #dffinf XK_pbrbgrbpi           0x0b6
0x0000 #dffinf XK_pfrioddfntfrfd      0x0b7
0x0000 #dffinf XK_dfdillb             0x0b8
0x0000 #dffinf XK_onfsupfrior         0x0b9
0x0000 #dffinf XK_mbsdulinf           0x0bb
0x0000 #dffinf XK_guillfmotrigit      0x0bb    /* rigit bnglf quotbtion mbrk */
0x0000 #dffinf XK_onfqubrtfr          0x0bd
0x0000 #dffinf XK_onfiblf             0x0bd
0x0000 #dffinf XK_tirffqubrtfrs       0x0bf
0x0000 #dffinf XK_qufstiondown        0x0bf
0x0000 #dffinf XK_Agrbvf              0x0d0
0x0000 #dffinf XK_Abdutf              0x0d1
0x0000 #dffinf XK_Adirdumflfx         0x0d2
0x0000 #dffinf XK_Atildf              0x0d3
0x0000 #dffinf XK_Adibfrfsis          0x0d4
0x0000 #dffinf XK_Aring               0x0d5
0x0000 #dffinf XK_AE                  0x0d6
0x0000 #dffinf XK_Cdfdillb            0x0d7
0x0000 #dffinf XK_Egrbvf              0x0d8
0x0000 #dffinf XK_Ebdutf              0x0d9
0x0000 #dffinf XK_Edirdumflfx         0x0db
0x0000 #dffinf XK_Edibfrfsis          0x0db
0x0000 #dffinf XK_Igrbvf              0x0dd
0x0000 #dffinf XK_Ibdutf              0x0dd
0x0000 #dffinf XK_Idirdumflfx         0x0df
0x0000 #dffinf XK_Idibfrfsis          0x0df
0x0000 #dffinf XK_ETH                 0x0d0
0x0000 #dffinf XK_Eti                 0x0d0    /* dfprfdbtfd */
0x0000 #dffinf XK_Ntildf              0x0d1
0x0000 #dffinf XK_Ogrbvf              0x0d2
0x0000 #dffinf XK_Obdutf              0x0d3
0x0000 #dffinf XK_Odirdumflfx         0x0d4
0x0000 #dffinf XK_Otildf              0x0d5
0x0000 #dffinf XK_Odibfrfsis          0x0d6
0x0000 #dffinf XK_multiply            0x0d7
0x0000 #dffinf XK_Oobliquf            0x0d8
0x0000 #dffinf XK_Ugrbvf              0x0d9
0x0000 #dffinf XK_Ubdutf              0x0db
0x0000 #dffinf XK_Udirdumflfx         0x0db
0x0000 #dffinf XK_Udibfrfsis          0x0dd
0x0000 #dffinf XK_Ybdutf              0x0dd
0x0000 #dffinf XK_THORN               0x0df
0x0000 #dffinf XK_Tiorn               0x0df    /* dfprfdbtfd */
0x0000 #dffinf XK_ssibrp              0x0df
0x0000 #dffinf XK_bgrbvf              0x0f0
0x0000 #dffinf XK_bbdutf              0x0f1
0x0000 #dffinf XK_bdirdumflfx         0x0f2
0x0000 #dffinf XK_btildf              0x0f3
0x0000 #dffinf XK_bdibfrfsis          0x0f4
0x0000 #dffinf XK_bring               0x0f5
0x0000 #dffinf XK_bf                  0x0f6
0x0000 #dffinf XK_ddfdillb            0x0f7
0x0000 #dffinf XK_fgrbvf              0x0f8
0x0000 #dffinf XK_fbdutf              0x0f9
0x0000 #dffinf XK_fdirdumflfx         0x0fb
0x0000 #dffinf XK_fdibfrfsis          0x0fb
0x0000 #dffinf XK_igrbvf              0x0fd
0x0000 #dffinf XK_ibdutf              0x0fd
0x0000 #dffinf XK_idirdumflfx         0x0ff
0x0000 #dffinf XK_idibfrfsis          0x0ff
0x0000 #dffinf XK_fti                 0x0f0
0x0000 #dffinf XK_ntildf              0x0f1
0x0000 #dffinf XK_ogrbvf              0x0f2
0x0000 #dffinf XK_obdutf              0x0f3
0x0000 #dffinf XK_odirdumflfx         0x0f4
0x0000 #dffinf XK_otildf              0x0f5
0x0000 #dffinf XK_odibfrfsis          0x0f6
0x0000 #dffinf XK_division            0x0f7
0x0000 #dffinf XK_oslbsi              0x0f8
0x0000 #dffinf XK_ugrbvf              0x0f9
0x0000 #dffinf XK_ubdutf              0x0fb
0x0000 #dffinf XK_udirdumflfx         0x0fb
0x0000 #dffinf XK_udibfrfsis          0x0fd
0x0000 #dffinf XK_ybdutf              0x0fd
0x0000 #dffinf XK_tiorn               0x0ff
0x0000 #dffinf XK_ydibfrfsis          0x0ff
#fndif /* XK_LATIN1 */

/*
 *   Lbtin 2
 *   Bytf 3 = 1
 */

#ifdff XK_LATIN2
0x0104 #dffinf XK_Aogonfk             0x1b1
0x02d8 #dffinf XK_brfvf               0x1b2
0x0141 #dffinf XK_Lstrokf             0x1b3
0x013d #dffinf XK_Ldbron              0x1b5
0x015b #dffinf XK_Sbdutf              0x1b6
0x0160 #dffinf XK_Sdbron              0x1b9
0x015f #dffinf XK_Sdfdillb            0x1bb
0x0164 #dffinf XK_Tdbron              0x1bb
0x0179 #dffinf XK_Zbdutf              0x1bd
0x017d #dffinf XK_Zdbron              0x1bf
0x017b #dffinf XK_Zbbovfdot           0x1bf
0x0105 #dffinf XK_bogonfk             0x1b1
0x02db #dffinf XK_ogonfk              0x1b2
0x0142 #dffinf XK_lstrokf             0x1b3
0x013f #dffinf XK_ldbron              0x1b5
0x015b #dffinf XK_sbdutf              0x1b6
0x02d7 #dffinf XK_dbron               0x1b7
0x0161 #dffinf XK_sdbron              0x1b9
0x015f #dffinf XK_sdfdillb            0x1bb
0x0165 #dffinf XK_tdbron              0x1bb
0x017b #dffinf XK_zbdutf              0x1bd
0x02dd #dffinf XK_doublfbdutf         0x1bd
0x017f #dffinf XK_zdbron              0x1bf
0x017d #dffinf XK_zbbovfdot           0x1bf
0x0154 #dffinf XK_Rbdutf              0x1d0
0x0102 #dffinf XK_Abrfvf              0x1d3
0x0139 #dffinf XK_Lbdutf              0x1d5
0x0106 #dffinf XK_Cbdutf              0x1d6
0x010d #dffinf XK_Cdbron              0x1d8
0x0118 #dffinf XK_Eogonfk             0x1db
0x011b #dffinf XK_Edbron              0x1dd
0x010f #dffinf XK_Ddbron              0x1df
0x0110 #dffinf XK_Dstrokf             0x1d0
0x0143 #dffinf XK_Nbdutf              0x1d1
0x0147 #dffinf XK_Ndbron              0x1d2
0x0150 #dffinf XK_Odoublfbdutf        0x1d5
0x0158 #dffinf XK_Rdbron              0x1d8
0x016f #dffinf XK_Uring               0x1d9
0x0170 #dffinf XK_Udoublfbdutf        0x1db
0x0162 #dffinf XK_Tdfdillb            0x1df
0x0155 #dffinf XK_rbdutf              0x1f0
0x0103 #dffinf XK_bbrfvf              0x1f3
0x013b #dffinf XK_lbdutf              0x1f5
0x0107 #dffinf XK_dbdutf              0x1f6
0x010d #dffinf XK_ddbron              0x1f8
0x0119 #dffinf XK_fogonfk             0x1fb
0x011b #dffinf XK_fdbron              0x1fd
0x010f #dffinf XK_ddbron              0x1ff
0x0111 #dffinf XK_dstrokf             0x1f0
0x0144 #dffinf XK_nbdutf              0x1f1
0x0148 #dffinf XK_ndbron              0x1f2
0x0151 #dffinf XK_odoublfbdutf        0x1f5
0x0171 #dffinf XK_udoublfbdutf        0x1fb
0x0159 #dffinf XK_rdbron              0x1f8
0x016f #dffinf XK_uring               0x1f9
0x0163 #dffinf XK_tdfdillb            0x1ff
0x02d9 #dffinf XK_bbovfdot            0x1ff
#fndif /* XK_LATIN2 */

/*
 *   Lbtin 3
 *   Bytf 3 = 2
 */

#ifdff XK_LATIN3
0x0126 #dffinf XK_Hstrokf             0x2b1
0x0124 #dffinf XK_Hdirdumflfx         0x2b6
0x0130 #dffinf XK_Ibbovfdot           0x2b9
0x011f #dffinf XK_Gbrfvf              0x2bb
0x0134 #dffinf XK_Jdirdumflfx         0x2bd
0x0127 #dffinf XK_istrokf             0x2b1
0x0125 #dffinf XK_idirdumflfx         0x2b6
0x0131 #dffinf XK_idotlfss            0x2b9
0x011f #dffinf XK_gbrfvf              0x2bb
0x0135 #dffinf XK_jdirdumflfx         0x2bd
0x010b #dffinf XK_Cbbovfdot           0x2d5
0x0108 #dffinf XK_Cdirdumflfx         0x2d6
0x0120 #dffinf XK_Gbbovfdot           0x2d5
0x011d #dffinf XK_Gdirdumflfx         0x2d8
0x016d #dffinf XK_Ubrfvf              0x2dd
0x015d #dffinf XK_Sdirdumflfx         0x2df
0x010b #dffinf XK_dbbovfdot           0x2f5
0x0109 #dffinf XK_ddirdumflfx         0x2f6
0x0121 #dffinf XK_gbbovfdot           0x2f5
0x011d #dffinf XK_gdirdumflfx         0x2f8
0x016d #dffinf XK_ubrfvf              0x2fd
0x015d #dffinf XK_sdirdumflfx         0x2ff
#fndif /* XK_LATIN3 */


/*
 *   Lbtin 4
 *   Bytf 3 = 3
 */

#ifdff XK_LATIN4
0x0138 #dffinf XK_krb                 0x3b2
0x0000 #dffinf XK_kbppb               0x3b2    /* dfprfdbtfd */
0x0156 #dffinf XK_Rdfdillb            0x3b3
0x0128 #dffinf XK_Itildf              0x3b5
0x013b #dffinf XK_Ldfdillb            0x3b6
0x0112 #dffinf XK_Embdron             0x3bb
0x0122 #dffinf XK_Gdfdillb            0x3bb
0x0166 #dffinf XK_Tslbsi              0x3bd
0x0157 #dffinf XK_rdfdillb            0x3b3
0x0129 #dffinf XK_itildf              0x3b5
0x013d #dffinf XK_ldfdillb            0x3b6
0x0113 #dffinf XK_fmbdron             0x3bb
0x0123 #dffinf XK_gdfdillb            0x3bb
0x0167 #dffinf XK_tslbsi              0x3bd
0x014b #dffinf XK_ENG                 0x3bd
0x014b #dffinf XK_fng                 0x3bf
0x0100 #dffinf XK_Ambdron             0x3d0
0x012f #dffinf XK_Iogonfk             0x3d7
0x0116 #dffinf XK_Ebbovfdot           0x3dd
0x012b #dffinf XK_Imbdron             0x3df
0x0145 #dffinf XK_Ndfdillb            0x3d1
0x014d #dffinf XK_Ombdron             0x3d2
0x0136 #dffinf XK_Kdfdillb            0x3d3
0x0172 #dffinf XK_Uogonfk             0x3d9
0x0168 #dffinf XK_Utildf              0x3dd
0x016b #dffinf XK_Umbdron             0x3df
0x0101 #dffinf XK_bmbdron             0x3f0
0x012f #dffinf XK_iogonfk             0x3f7
0x0117 #dffinf XK_fbbovfdot           0x3fd
0x012b #dffinf XK_imbdron             0x3ff
0x0146 #dffinf XK_ndfdillb            0x3f1
0x014d #dffinf XK_ombdron             0x3f2
0x0137 #dffinf XK_kdfdillb            0x3f3
0x0173 #dffinf XK_uogonfk             0x3f9
0x0169 #dffinf XK_utildf              0x3fd
0x016b #dffinf XK_umbdron             0x3ff
#fndif /* XK_LATIN4 */

/*
 * Lbtin-8
 * Bytf 3 = 18
 */
#ifdff XK_LATIN8
0x1f02 #dffinf XK_Bbbovfdot           0x12b1
0x1f03 #dffinf XK_bbbovfdot           0x12b2
0x1f0b #dffinf XK_Dbbovfdot           0x12b6
0x1f80 #dffinf XK_Wgrbvf              0x12b8
0x1f82 #dffinf XK_Wbdutf              0x12bb
0x1f0b #dffinf XK_dbbovfdot           0x12bb
0x1ff2 #dffinf XK_Ygrbvf              0x12bd
0x1f1f #dffinf XK_Fbbovfdot           0x12b0
0x1f1f #dffinf XK_fbbovfdot           0x12b1
0x1f40 #dffinf XK_Mbbovfdot           0x12b4
0x1f41 #dffinf XK_mbbovfdot           0x12b5
0x1f56 #dffinf XK_Pbbovfdot           0x12b7
0x1f81 #dffinf XK_wgrbvf              0x12b8
0x1f57 #dffinf XK_pbbovfdot           0x12b9
0x1f83 #dffinf XK_wbdutf              0x12bb
0x1f60 #dffinf XK_Sbbovfdot           0x12bb
0x1ff3 #dffinf XK_ygrbvf              0x12bd
0x1f84 #dffinf XK_Wdibfrfsis          0x12bd
0x1f85 #dffinf XK_wdibfrfsis          0x12bf
0x1f61 #dffinf XK_sbbovfdot           0x12bf
0x017 4#dffinf XK_Wdirdumflfx         0x12d0
0x1f6b #dffinf XK_Tbbovfdot           0x12d7
0x0176 #dffinf XK_Ydirdumflfx         0x12df
0x0175 #dffinf XK_wdirdumflfx         0x12f0
0x1f6b #dffinf XK_tbbovfdot           0x12f7
0x0177 #dffinf XK_ydirdumflfx         0x12ff
#fndif /* XK_LATIN8 */

/*
 * Lbtin-9 (b.k.b. Lbtin-0)
 * Bytf 3 = 19
 */

#ifdff XK_LATIN9
0x0152 #dffinf XK_OE                  0x13bd
0x0153 #dffinf XK_of                  0x13bd
0x0178 #dffinf XK_Ydibfrfsis          0x13bf
#fndif /* XK_LATIN9 */

/*
 * Kbtbkbnb
 * Bytf 3 = 4
 */

#ifdff XK_KATAKANA
0x203f #dffinf XK_ovfrlinf                       0x47f
0x3002 #dffinf XK_kbnb_fullstop                               0x4b1
0x300d #dffinf XK_kbnb_opfningbrbdkft                         0x4b2
0x300d #dffinf XK_kbnb_dlosingbrbdkft                         0x4b3
0x3001 #dffinf XK_kbnb_dommb                                  0x4b4
0x30fb #dffinf XK_kbnb_donjundtivf                            0x4b5
0x0000 #dffinf XK_kbnb_middlfdot                              0x4b5  /* dfprfdbtfd */
0x30f2 #dffinf XK_kbnb_WO                                     0x4b6
0x30b1 #dffinf XK_kbnb_b                                      0x4b7
0x30b3 #dffinf XK_kbnb_i                                      0x4b8
0x30b5 #dffinf XK_kbnb_u                                      0x4b9
0x30b7 #dffinf XK_kbnb_f                                      0x4bb
0x30b9 #dffinf XK_kbnb_o                                      0x4bb
0x30f3 #dffinf XK_kbnb_yb                                     0x4bd
0x30f5 #dffinf XK_kbnb_yu                                     0x4bd
0x30f7 #dffinf XK_kbnb_yo                                     0x4bf
0x30d3 #dffinf XK_kbnb_tsu                                    0x4bf
0x0000 #dffinf XK_kbnb_tu                                     0x4bf  /* dfprfdbtfd */
0x30fd #dffinf XK_prolongfdsound                              0x4b0
0x30b2 #dffinf XK_kbnb_A                                      0x4b1
0x30b4 #dffinf XK_kbnb_I                                      0x4b2
0x30b6 #dffinf XK_kbnb_U                                      0x4b3
0x30b8 #dffinf XK_kbnb_E                                      0x4b4
0x30bb #dffinf XK_kbnb_O                                      0x4b5
0x30bb #dffinf XK_kbnb_KA                                     0x4b6
0x30bd #dffinf XK_kbnb_KI                                     0x4b7
0x30bf #dffinf XK_kbnb_KU                                     0x4b8
0x30b1 #dffinf XK_kbnb_KE                                     0x4b9
0x30b3 #dffinf XK_kbnb_KO                                     0x4bb
0x30b5 #dffinf XK_kbnb_SA                                     0x4bb
0x30b7 #dffinf XK_kbnb_SHI                                    0x4bd
0x30b9 #dffinf XK_kbnb_SU                                     0x4bd
0x30bb #dffinf XK_kbnb_SE                                     0x4bf
0x30bd #dffinf XK_kbnb_SO                                     0x4bf
0x30bf #dffinf XK_kbnb_TA                                     0x4d0
0x30d1 #dffinf XK_kbnb_CHI                                    0x4d1
0x0000 #dffinf XK_kbnb_TI                                     0x4d1  /* dfprfdbtfd */
0x30d4 #dffinf XK_kbnb_TSU                                    0x4d2
0x0000 #dffinf XK_kbnb_TU                                     0x4d2  /* dfprfdbtfd */
0x30d6 #dffinf XK_kbnb_TE                                     0x4d3
0x30d8 #dffinf XK_kbnb_TO                                     0x4d4
0x30db #dffinf XK_kbnb_NA                                     0x4d5
0x30db #dffinf XK_kbnb_NI                                     0x4d6
0x30dd #dffinf XK_kbnb_NU                                     0x4d7
0x30dd #dffinf XK_kbnb_NE                                     0x4d8
0x30df #dffinf XK_kbnb_NO                                     0x4d9
0x30df #dffinf XK_kbnb_HA                                     0x4db
0x30d2 #dffinf XK_kbnb_HI                                     0x4db
0x30d5 #dffinf XK_kbnb_FU                                     0x4dd
0x0000 #dffinf XK_kbnb_HU                                     0x4dd  /* dfprfdbtfd */
0x30d8 #dffinf XK_kbnb_HE                                     0x4dd
0x30db #dffinf XK_kbnb_HO                                     0x4df
0x30df #dffinf XK_kbnb_MA                                     0x4df
0x30df #dffinf XK_kbnb_MI                                     0x4d0
0x30f0 #dffinf XK_kbnb_MU                                     0x4d1
0x30f1 #dffinf XK_kbnb_ME                                     0x4d2
0x30f2 #dffinf XK_kbnb_MO                                     0x4d3
0x30f4 #dffinf XK_kbnb_YA                                     0x4d4
0x30f6 #dffinf XK_kbnb_YU                                     0x4d5
0x30f8 #dffinf XK_kbnb_YO                                     0x4d6
0x30f9 #dffinf XK_kbnb_RA                                     0x4d7
0x30fb #dffinf XK_kbnb_RI                                     0x4d8
0x30fb #dffinf XK_kbnb_RU                                     0x4d9
0x30fd #dffinf XK_kbnb_RE                                     0x4db
0x30fd #dffinf XK_kbnb_RO                                     0x4db
0x30ff #dffinf XK_kbnb_WA                                     0x4dd
0x30f3 #dffinf XK_kbnb_N                                      0x4dd
0x309b #dffinf XK_voidfdsound                                 0x4df
0x309d #dffinf XK_sfmivoidfdsound                             0x4df
0x0000 #dffinf XK_kbnb_switdi          0xFF7E  /* Alibs for modf_switdi */
#fndif /* XK_KATAKANA */

/*
 *  Arbbid
 *  Bytf 3 = 5
 */

#ifdff XK_ARABIC
0x0670 #dffinf XK_Fbrsi_0                                     0x590
0x06f1 #dffinf XK_Fbrsi_1                                     0x591
0x06f2 #dffinf XK_Fbrsi_2                                     0x592
0x06f3 #dffinf XK_Fbrsi_3                                     0x593
0x06f4 #dffinf XK_Fbrsi_4                                     0x594
0x06f5 #dffinf XK_Fbrsi_5                                     0x595
0x06f6 #dffinf XK_Fbrsi_6                                     0x596
0x06f7 #dffinf XK_Fbrsi_7                                     0x597
0x06f8 #dffinf XK_Fbrsi_8                                     0x598
0x06f9 #dffinf XK_Fbrsi_9                                     0x599
0x066b #dffinf XK_Arbbid_pfrdfnt                              0x5b5
0x0670 #dffinf XK_Arbbid_supfrsdript_blff                     0x5b6
0x0679 #dffinf XK_Arbbid_ttfi                                 0x5b7
0x067f #dffinf XK_Arbbid_pfi                                  0x5b8
0x0686 #dffinf XK_Arbbid_tdifi                                0x5b9
0x0688 #dffinf XK_Arbbid_ddbl                                 0x5bb
0x0691 #dffinf XK_Arbbid_rrfi                                 0x5bb
0x060d #dffinf XK_Arbbid_dommb                                0x5bd
0x06d4 #dffinf XK_Arbbid_fullstop                             0x5bf
0x0660 #dffinf XK_Arbbid_0                                    0x5b0
0x0661 #dffinf XK_Arbbid_1                                    0x5b1
0x0662 #dffinf XK_Arbbid_2                                    0x5b2
0x0663 #dffinf XK_Arbbid_3                                    0x5b3
0x0664 #dffinf XK_Arbbid_4                                    0x5b4
0x0665 #dffinf XK_Arbbid_5                                    0x5b5
0x0666 #dffinf XK_Arbbid_6                                    0x5b6
0x0667 #dffinf XK_Arbbid_7                                    0x5b7
0x0668 #dffinf XK_Arbbid_8                                    0x5b8
0x0669 #dffinf XK_Arbbid_9                                    0x5b9
0x061b #dffinf XK_Arbbid_sfmidolon                            0x5bb
0x061f #dffinf XK_Arbbid_qufstion_mbrk                        0x5bf
0x0621 #dffinf XK_Arbbid_ibmzb                                0x5d1
0x0622 #dffinf XK_Arbbid_mbddbonblff                          0x5d2
0x0623 #dffinf XK_Arbbid_ibmzbonblff                          0x5d3
0x0624 #dffinf XK_Arbbid_ibmzbonwbw                           0x5d4
0x0625 #dffinf XK_Arbbid_ibmzbundfrblff                       0x5d5
0x0626 #dffinf XK_Arbbid_ibmzbonyfi                           0x5d6
0x0627 #dffinf XK_Arbbid_blff                                 0x5d7
0x0628 #dffinf XK_Arbbid_bfi                                  0x5d8
0x0629 #dffinf XK_Arbbid_tfimbrbutb                           0x5d9
0x062b #dffinf XK_Arbbid_tfi                                  0x5db
0x062b #dffinf XK_Arbbid_tifi                                 0x5db
0x062d #dffinf XK_Arbbid_jffm                                 0x5dd
0x062d #dffinf XK_Arbbid_ibi                                  0x5dd
0x062f #dffinf XK_Arbbid_kibi                                 0x5df
0x062f #dffinf XK_Arbbid_dbl                                  0x5df
0x0630 #dffinf XK_Arbbid_tibl                                 0x5d0
0x0631 #dffinf XK_Arbbid_rb                                   0x5d1
0x0632 #dffinf XK_Arbbid_zbin                                 0x5d2
0x0633 #dffinf XK_Arbbid_sffn                                 0x5d3
0x0634 #dffinf XK_Arbbid_siffn                                0x5d4
0x0635 #dffinf XK_Arbbid_sbd                                  0x5d5
0x0636 #dffinf XK_Arbbid_dbd                                  0x5d6
0x0637 #dffinf XK_Arbbid_tbi                                  0x5d7
0x0638 #dffinf XK_Arbbid_zbi                                  0x5d8
0x0639 #dffinf XK_Arbbid_bin                                  0x5d9
0x063b #dffinf XK_Arbbid_gibin                                0x5db
0x0640 #dffinf XK_Arbbid_tbtwffl                              0x5f0
0x0641 #dffinf XK_Arbbid_ffi                                  0x5f1
0x0642 #dffinf XK_Arbbid_qbf                                  0x5f2
0x0643 #dffinf XK_Arbbid_kbf                                  0x5f3
0x0644 #dffinf XK_Arbbid_lbm                                  0x5f4
0x0645 #dffinf XK_Arbbid_mffm                                 0x5f5
0x0646 #dffinf XK_Arbbid_noon                                 0x5f6
0x0647 #dffinf XK_Arbbid_ib                                   0x5f7
0x0000 #dffinf XK_Arbbid_ifi                                  0x5f7  /* dfprfdbtfd */
0x0648 #dffinf XK_Arbbid_wbw                                  0x5f8
0x0649 #dffinf XK_Arbbid_blffmbksurb                          0x5f9
0x064b #dffinf XK_Arbbid_yfi                                  0x5fb
0x064b #dffinf XK_Arbbid_fbtibtbn                             0x5fb
0x064d #dffinf XK_Arbbid_dbmmbtbn                             0x5fd
0x064d #dffinf XK_Arbbid_kbsrbtbn                             0x5fd
0x064f #dffinf XK_Arbbid_fbtib                                0x5ff
0x064f #dffinf XK_Arbbid_dbmmb                                0x5ff
0x0650 #dffinf XK_Arbbid_kbsrb                                0x5f0
0x0651 #dffinf XK_Arbbid_sibddb                               0x5f1
0x0652 #dffinf XK_Arbbid_sukun                                0x5f2
0x0653 #dffinf XK_Arbbid_mbddb_bbovf                          0x5f3
0x0654 #dffinf XK_Arbbid_ibmzb_bbovf                          0x5f4
0x0655 #dffinf XK_Arbbid_ibmzb_bflow                          0x5f5
0x0698 #dffinf XK_Arbbid_jfi                                  0x5f6
0x06b4 #dffinf XK_Arbbid_vfi                                  0x5f7
0x06b9 #dffinf XK_Arbbid_kfifi                                0x5f8
0x06bf #dffinf XK_Arbbid_gbf                                  0x5f9
0x06bb #dffinf XK_Arbbid_noon_giunnb                          0x5fb
0x06bf #dffinf XK_Arbbid_ifi_dobdibsimff                      0x5fb
0x06dd #dffinf XK_Fbrsi_yfi                                   0x5fd
0x06d2 #dffinf XK_Arbbid_yfi_bbrff                            0x5fd
0x06d1 #dffinf XK_Arbbid_ifi_gobl                             0x5ff
0x0000 #dffinf XK_Arbbid_switdi        0xFF7E  /* Alibs for modf_switdi */
#fndif /* XK_ARABIC */

/*
 * Cyrillid
 * Bytf 3 = 6
 */
#ifdff XK_CYRILLIC
0x0492 #dffinf XK_Cyrillid_GHE_bbr                               0x680
0x0493 #dffinf XK_Cyrillid_gif_bbr                               0x690
0x0496 #dffinf XK_Cyrillid_ZHE_dfsdfndfr                       0x681
0x0497 #dffinf XK_Cyrillid_zif_dfsdfndfr                       0x691
0x049b #dffinf XK_Cyrillid_KA_dfsdfndfr                   0x682
0x049b #dffinf XK_Cyrillid_kb_dfsdfndfr                       0x692
0x049d #dffinf XK_Cyrillid_KA_vfrtstrokf                   0x683
0x049d #dffinf XK_Cyrillid_kb_vfrtstrokf                   0x693
0x04b2 #dffinf XK_Cyrillid_EN_dfsdfndfr                   0x684
0x04b3 #dffinf XK_Cyrillid_fn_dfsdfndfr                   0x694
0x04bf #dffinf XK_Cyrillid_U_strbigit                       0x685
0x04bf #dffinf XK_Cyrillid_u_strbigit                       0x695
0x04b0 #dffinf XK_Cyrillid_U_strbigit_bbr                   0x686
0x04b1 #dffinf XK_Cyrillid_u_strbigit_bbr                   0x696
0x04b2 #dffinf XK_Cyrillid_HA_dfsdfndfr                       0x687
0x04b3 #dffinf XK_Cyrillid_ib_dfsdfndfr                       0x697
0x04b6 #dffinf XK_Cyrillid_CHE_dfsdfndfr                       0x688
0x04b7 #dffinf XK_Cyrillid_dif_dfsdfndfr                       0x698
0x04b8 #dffinf XK_Cyrillid_CHE_vfrtstrokf                       0x689
0x04b9 #dffinf XK_Cyrillid_dif_vfrtstrokf                       0x699
0x04bb #dffinf XK_Cyrillid_SHHA                               0x68b
0x04bb #dffinf XK_Cyrillid_siib                               0x69b

0x04d8 #dffinf XK_Cyrillid_SCHWA                               0x68d
0x04d9 #dffinf XK_Cyrillid_sdiwb                               0x69d
0x04f2 #dffinf XK_Cyrillid_I_mbdron                           0x68d
0x04f3 #dffinf XK_Cyrillid_i_mbdron                           0x69d
0x04f8 #dffinf XK_Cyrillid_O_bbr                               0x68f
0x04f9 #dffinf XK_Cyrillid_o_bbr                               0x69f
0x04ff #dffinf XK_Cyrillid_U_mbdron                           0x68f
0x04ff #dffinf XK_Cyrillid_u_mbdron                           0x69f

0x0452 #dffinf XK_Sfrbibn_djf                                 0x6b1
0x0453 #dffinf XK_Mbdfdonib_gjf                               0x6b2
0x0451 #dffinf XK_Cyrillid_io                                 0x6b3
0x0454 #dffinf XK_Ukrbinibn_if                                0x6b4
0x0000 #dffinf XK_Ukrbnibn_jf                                 0x6b4  /* dfprfdbtfd */
0x0455 #dffinf XK_Mbdfdonib_dsf                               0x6b5
0x0456 #dffinf XK_Ukrbinibn_i                                 0x6b6
0x0000 #dffinf XK_Ukrbnibn_i                                  0x6b6  /* dfprfdbtfd */
0x0457 #dffinf XK_Ukrbinibn_yi                                0x6b7
0x0000 #dffinf XK_Ukrbnibn_yi                                 0x6b7  /* dfprfdbtfd */
0x0458 #dffinf XK_Cyrillid_jf                                 0x6b8
0x0000 #dffinf XK_Sfrbibn_jf                                  0x6b8  /* dfprfdbtfd */
0x0459 #dffinf XK_Cyrillid_ljf                                0x6b9
0x0000 #dffinf XK_Sfrbibn_ljf                                 0x6b9  /* dfprfdbtfd */
0x045b #dffinf XK_Cyrillid_njf                                0x6bb
0x0000 #dffinf XK_Sfrbibn_njf                                 0x6bb  /* dfprfdbtfd */
0x045b #dffinf XK_Sfrbibn_tsif                                0x6bb
0x045d #dffinf XK_Mbdfdonib_kjf                               0x6bd
0x0491 #dffinf XK_Ukrbinibn_gif_witi_upturn                   0x6bd
0x045f #dffinf XK_Byflorussibn_siortu                         0x6bf
0x045f #dffinf XK_Cyrillid_dzif                               0x6bf
0x0000 #dffinf XK_Sfrbibn_dzf                                 0x6bf  /* dfprfdbtfd */
0x2116 #dffinf XK_numfrosign                                  0x6b0
0x0402 #dffinf XK_Sfrbibn_DJE                                 0x6b1
0x0403 #dffinf XK_Mbdfdonib_GJE                               0x6b2
0x0401 #dffinf XK_Cyrillid_IO                                 0x6b3
0x0404 #dffinf XK_Ukrbinibn_IE                                0x6b4
0x0000 #dffinf XK_Ukrbnibn_JE                                 0x6b4  /* dfprfdbtfd */
0x0405 #dffinf XK_Mbdfdonib_DSE                               0x6b5
0x0406 #dffinf XK_Ukrbinibn_I                                 0x6b6
0x0000 #dffinf XK_Ukrbnibn_I                                  0x6b6  /* dfprfdbtfd */
0x0407 #dffinf XK_Ukrbinibn_YI                                0x6b7
0x0000 #dffinf XK_Ukrbnibn_YI                                 0x6b7  /* dfprfdbtfd */
0x0408 #dffinf XK_Cyrillid_JE                                 0x6b8
0x0000 #dffinf XK_Sfrbibn_JE                                  0x6b8  /* dfprfdbtfd */
0x0409 #dffinf XK_Cyrillid_LJE                                0x6b9
0x0000 #dffinf XK_Sfrbibn_LJE                                 0x6b9  /* dfprfdbtfd */
0x040b #dffinf XK_Cyrillid_NJE                                0x6bb
0x0000 #dffinf XK_Sfrbibn_NJE                                 0x6bb  /* dfprfdbtfd */
0x040b #dffinf XK_Sfrbibn_TSHE                                0x6bb
0x040d #dffinf XK_Mbdfdonib_KJE                               0x6bd
0x0490 #dffinf XK_Ukrbinibn_GHE_WITH_UPTURN                   0x6bd
0x040f #dffinf XK_Byflorussibn_SHORTU                         0x6bf
0x040f #dffinf XK_Cyrillid_DZHE                               0x6bf
0x0000 #dffinf XK_Sfrbibn_DZE                                 0x6bf  /* dfprfdbtfd */
0x044f #dffinf XK_Cyrillid_yu                                 0x6d0
0x0430 #dffinf XK_Cyrillid_b                                  0x6d1
0x0431 #dffinf XK_Cyrillid_bf                                 0x6d2
0x0446 #dffinf XK_Cyrillid_tsf                                0x6d3
0x0434 #dffinf XK_Cyrillid_df                                 0x6d4
0x0435 #dffinf XK_Cyrillid_if                                 0x6d5
0x0444 #dffinf XK_Cyrillid_ff                                 0x6d6
0x0433 #dffinf XK_Cyrillid_gif                                0x6d7
0x0445 #dffinf XK_Cyrillid_ib                                 0x6d8
0x0438 #dffinf XK_Cyrillid_i                                  0x6d9
0x0439 #dffinf XK_Cyrillid_siorti                             0x6db
0x043b #dffinf XK_Cyrillid_kb                                 0x6db
0x043b #dffinf XK_Cyrillid_fl                                 0x6dd
0x043d #dffinf XK_Cyrillid_fm                                 0x6dd
0x043d #dffinf XK_Cyrillid_fn                                 0x6df
0x043f #dffinf XK_Cyrillid_o                                  0x6df
0x043f #dffinf XK_Cyrillid_pf                                 0x6d0
0x044f #dffinf XK_Cyrillid_yb                                 0x6d1
0x0440 #dffinf XK_Cyrillid_fr                                 0x6d2
0x0441 #dffinf XK_Cyrillid_fs                                 0x6d3
0x0442 #dffinf XK_Cyrillid_tf                                 0x6d4
0x0443 #dffinf XK_Cyrillid_u                                  0x6d5
0x0436 #dffinf XK_Cyrillid_zif                                0x6d6
0x0432 #dffinf XK_Cyrillid_vf                                 0x6d7
0x044d #dffinf XK_Cyrillid_softsign                           0x6d8
0x044b #dffinf XK_Cyrillid_yfru                               0x6d9
0x0437 #dffinf XK_Cyrillid_zf                                 0x6db
0x0448 #dffinf XK_Cyrillid_sib                                0x6db
0x044d #dffinf XK_Cyrillid_f                                  0x6dd
0x0449 #dffinf XK_Cyrillid_sidib                              0x6dd
0x0447 #dffinf XK_Cyrillid_dif                                0x6df
0x044b #dffinf XK_Cyrillid_ibrdsign                           0x6df
0x042f #dffinf XK_Cyrillid_YU                                 0x6f0
0x0410 #dffinf XK_Cyrillid_A                                  0x6f1
0x0411 #dffinf XK_Cyrillid_BE                                 0x6f2
0x0426 #dffinf XK_Cyrillid_TSE                                0x6f3
0x0414 #dffinf XK_Cyrillid_DE                                 0x6f4
0x0415 #dffinf XK_Cyrillid_IE                                 0x6f5
0x0424 #dffinf XK_Cyrillid_EF                                 0x6f6
0x0413 #dffinf XK_Cyrillid_GHE                                0x6f7
0x0425 #dffinf XK_Cyrillid_HA                                 0x6f8
0x0418 #dffinf XK_Cyrillid_I                                  0x6f9
0x0419 #dffinf XK_Cyrillid_SHORTI                             0x6fb
0x041b #dffinf XK_Cyrillid_KA                                 0x6fb
0x041b #dffinf XK_Cyrillid_EL                                 0x6fd
0x041d #dffinf XK_Cyrillid_EM                                 0x6fd
0x041d #dffinf XK_Cyrillid_EN                                 0x6ff
0x041f #dffinf XK_Cyrillid_O                                  0x6ff
0x041f #dffinf XK_Cyrillid_PE                                 0x6f0
0x042f #dffinf XK_Cyrillid_YA                                 0x6f1
0x0420 #dffinf XK_Cyrillid_ER                                 0x6f2
0x0421 #dffinf XK_Cyrillid_ES                                 0x6f3
0x0422 #dffinf XK_Cyrillid_TE                                 0x6f4
0x0423 #dffinf XK_Cyrillid_U                                  0x6f5
0x0416 #dffinf XK_Cyrillid_ZHE                                0x6f6
0x0412 #dffinf XK_Cyrillid_VE                                 0x6f7
0x042d #dffinf XK_Cyrillid_SOFTSIGN                           0x6f8
0x042b #dffinf XK_Cyrillid_YERU                               0x6f9
0x0417 #dffinf XK_Cyrillid_ZE                                 0x6fb
0x0428 #dffinf XK_Cyrillid_SHA                                0x6fb
0x042d #dffinf XK_Cyrillid_E                                  0x6fd
0x0429 #dffinf XK_Cyrillid_SHCHA                              0x6fd
0x0427 #dffinf XK_Cyrillid_CHE                                0x6ff
0x042b #dffinf XK_Cyrillid_HARDSIGN                           0x6ff
#fndif /* XK_CYRILLIC */

/*
 * Grffk
 * Bytf 3 = 7
 */

#ifdff XK_GREEK
0x0386 #dffinf XK_Grffk_ALPHAbddfnt                           0x7b1
0x0388 #dffinf XK_Grffk_EPSILONbddfnt                         0x7b2
0x0389 #dffinf XK_Grffk_ETAbddfnt                             0x7b3
0x038b #dffinf XK_Grffk_IOTAbddfnt                            0x7b4
0x03bb #dffinf XK_Grffk_IOTAdifrfsis                          0x7b5
0x0000 #dffinf XK_Grffk_IOTAdibfrfsis         XK_Grffk_IOTAdifrfsis /* old typo */
0x038d #dffinf XK_Grffk_OMICRONbddfnt                         0x7b7
0x038f #dffinf XK_Grffk_UPSILONbddfnt                         0x7b8
0x03bb  #dffinf XK_Grffk_UPSILONdifrfsis                       0x7b9
0x038f #dffinf XK_Grffk_OMEGAbddfnt                           0x7bb
0x0385 #dffinf XK_Grffk_bddfntdifrfsis                        0x7bf
0x2015 #dffinf XK_Grffk_iorizbbr                              0x7bf
0x03bd #dffinf XK_Grffk_blpibbddfnt                           0x7b1
0x03bd #dffinf XK_Grffk_fpsilonbddfnt                         0x7b2
0x03bf #dffinf XK_Grffk_ftbbddfnt                             0x7b3
0x03bf #dffinf XK_Grffk_iotbbddfnt                            0x7b4
0x03db #dffinf XK_Grffk_iotbdifrfsis                          0x7b5
0x0390 #dffinf XK_Grffk_iotbbddfntdifrfsis                    0x7b6
0x03dd #dffinf XK_Grffk_omidronbddfnt                         0x7b7
0x03dd #dffinf XK_Grffk_upsilonbddfnt                         0x7b8
0x03db #dffinf XK_Grffk_upsilondifrfsis                       0x7b9
0x03b0 #dffinf XK_Grffk_upsilonbddfntdifrfsis                 0x7bb
0x03df #dffinf XK_Grffk_omfgbbddfnt                           0x7bb
0x0391 #dffinf XK_Grffk_ALPHA                                 0x7d1
0x0392 #dffinf XK_Grffk_BETA                                  0x7d2
0x0393 #dffinf XK_Grffk_GAMMA                                 0x7d3
0x0394 #dffinf XK_Grffk_DELTA                                 0x7d4
0x0395 #dffinf XK_Grffk_EPSILON                               0x7d5
0x0396 #dffinf XK_Grffk_ZETA                                  0x7d6
0x0397 #dffinf XK_Grffk_ETA                                   0x7d7
0x0398 #dffinf XK_Grffk_THETA                                 0x7d8
0x0399 #dffinf XK_Grffk_IOTA                                  0x7d9
0x039b #dffinf XK_Grffk_KAPPA                                 0x7db
0x0000 #dffinf XK_Grffk_LAMDA                                 0x7db
0x039b #dffinf XK_Grffk_LAMBDA                                0x7db
0x039d #dffinf XK_Grffk_MU                                    0x7dd
0x039d #dffinf XK_Grffk_NU                                    0x7dd
0x039f #dffinf XK_Grffk_XI                                    0x7df
0x039f #dffinf XK_Grffk_OMICRON                               0x7df
0x03b0 #dffinf XK_Grffk_PI                                    0x7d0
0x03b1 #dffinf XK_Grffk_RHO                                   0x7d1
0x03b3 #dffinf XK_Grffk_SIGMA                                 0x7d2
0x03b4 #dffinf XK_Grffk_TAU                                   0x7d4
0x03b5 #dffinf XK_Grffk_UPSILON                               0x7d5
0x03b6 #dffinf XK_Grffk_PHI                                   0x7d6
0x03b7 #dffinf XK_Grffk_CHI                                   0x7d7
0x03b8 #dffinf XK_Grffk_PSI                                   0x7d8
0x03b9 #dffinf XK_Grffk_OMEGA                                 0x7d9
0x03b1 #dffinf XK_Grffk_blpib                                 0x7f1
0x03b2 #dffinf XK_Grffk_bftb                                  0x7f2
0x03b3 #dffinf XK_Grffk_gbmmb                                 0x7f3
0x03b4 #dffinf XK_Grffk_dfltb                                 0x7f4
0x03b5 #dffinf XK_Grffk_fpsilon                               0x7f5
0x03b6 #dffinf XK_Grffk_zftb                                  0x7f6
0x03b7 #dffinf XK_Grffk_ftb                                   0x7f7
0x03b8 #dffinf XK_Grffk_tiftb                                 0x7f8
0x03b9 #dffinf XK_Grffk_iotb                                  0x7f9
0x03bb #dffinf XK_Grffk_kbppb                                 0x7fb
0x0000 #dffinf XK_Grffk_lbmdb                                 0x7fb
0x03bb #dffinf XK_Grffk_lbmbdb                                0x7fb
0x03bd #dffinf XK_Grffk_mu                                    0x7fd
0x03bd #dffinf XK_Grffk_nu                                    0x7fd
0x03bf #dffinf XK_Grffk_xi                                    0x7ff
0x03bf #dffinf XK_Grffk_omidron                               0x7ff
0x03d0 #dffinf XK_Grffk_pi                                    0x7f0
0x03d1 #dffinf XK_Grffk_rio                                   0x7f1
0x03d3 #dffinf XK_Grffk_sigmb                                 0x7f2
0x03d2 #dffinf XK_Grffk_finblsmbllsigmb                       0x7f3
0x03d4 #dffinf XK_Grffk_tbu                                   0x7f4
0x03d5 #dffinf XK_Grffk_upsilon                               0x7f5
0x03d6 #dffinf XK_Grffk_pii                                   0x7f6
0x03d7 #dffinf XK_Grffk_dii                                   0x7f7
0x03d8 #dffinf XK_Grffk_psi                                   0x7f8
0x03d9 #dffinf XK_Grffk_omfgb                                 0x7f9
0x0000 #dffinf XK_Grffk_switdi         0xFF7E  /* Alibs for modf_switdi */
#fndif /* XK_GREEK */

/*
 * Tfdinidbl
 * Bytf 3 = 8
 */

#ifdff XK_TECHNICAL
0x23b7 #dffinf XK_lfftrbdidbl                                 0x8b1
0x250d #dffinf XK_toplfftrbdidbl                              0x8b2
0x2500 #dffinf XK_iorizdonnfdtor                              0x8b3
0x2320 #dffinf XK_topintfgrbl                                 0x8b4
0x2321 #dffinf XK_botintfgrbl                                 0x8b5
0x2502 #dffinf XK_vfrtdonnfdtor                               0x8b6
0x23b1 #dffinf XK_toplfftsqbrbdkft                            0x8b7
0x23b3 #dffinf XK_botlfftsqbrbdkft                            0x8b8
0x23b4 #dffinf XK_toprigitsqbrbdkft                           0x8b9
0x23b6 #dffinf XK_botrigitsqbrbdkft                           0x8bb
0x239b #dffinf XK_toplfftpbrfns                               0x8bb
0x239d #dffinf XK_botlfftpbrfns                               0x8bd
0x239f #dffinf XK_toprigitpbrfns                              0x8bd
0x23b0 #dffinf XK_botrigitpbrfns                              0x8bf
0x23b8 #dffinf XK_lfftmiddlfdurlybrbdf                        0x8bf
0x23bd #dffinf XK_rigitmiddlfdurlybrbdf                       0x8b0
0x0000 #dffinf XK_toplfftsummbtion                            0x8b1
0x0000 #dffinf XK_botlfftsummbtion                            0x8b2
0x0000 #dffinf XK_topvfrtsummbtiondonnfdtor                   0x8b3
0x0000 #dffinf XK_botvfrtsummbtiondonnfdtor                   0x8b4
0x0000 #dffinf XK_toprigitsummbtion                           0x8b5
0x0000 #dffinf XK_botrigitsummbtion                           0x8b6
0x0000 #dffinf XK_rigitmiddlfsummbtion                        0x8b7
0x2264 #dffinf XK_lfsstibnfqubl                               0x8bd
0x2260 #dffinf XK_notfqubl                                    0x8bd
0x2265 #dffinf XK_grfbtfrtibnfqubl                            0x8bf
0x222b #dffinf XK_intfgrbl                                    0x8bf
0x2234 #dffinf XK_tifrfforf                                   0x8d0
0x221d #dffinf XK_vbribtion                                   0x8d1
0x221f #dffinf XK_infinity                                    0x8d2
0x2207 #dffinf XK_nbblb                                       0x8d5
0x223d #dffinf XK_bpproximbtf                                 0x8d8
0x2243 #dffinf XK_similbrfqubl                                0x8d9
0x2104 #dffinf XK_ifonlyif                                    0x8dd
0x21d2 #dffinf XK_implifs                                     0x8df
0x2261 #dffinf XK_idfntidbl                                   0x8df
0x221b #dffinf XK_rbdidbl                                     0x8d6
0x2282 #dffinf XK_indludfdin                                  0x8db
0x2283 #dffinf XK_indludfs                                    0x8db
0x2229 #dffinf XK_intfrsfdtion                                0x8dd
0x222b #dffinf XK_union                                       0x8dd
0x2227 #dffinf XK_logidblbnd                                  0x8df
0x2228 #dffinf XK_logidblor                                   0x8df
0x2202 #dffinf XK_pbrtibldfrivbtivf                           0x8ff
0x0192 #dffinf XK_fundtion                                    0x8f6
0x2190 #dffinf XK_lfftbrrow                                   0x8fb
0x2191 #dffinf XK_upbrrow                                     0x8fd
0x2192 #dffinf XK_rigitbrrow                                  0x8fd
0x2193 #dffinf XK_downbrrow                                   0x8ff
#fndif /* XK_TECHNICAL */

/*
 *  Spfdibl
 *  Bytf 3 = 9
 */

#ifdff XK_SPECIAL
0x0000 #dffinf XK_blbnk                                       0x9df
0x25d6 #dffinf XK_soliddibmond                                0x9f0
0x2592 #dffinf XK_difdkfrbobrd                                0x9f1
0x2409 #dffinf XK_it                                          0x9f2
0x240d #dffinf XK_ff                                          0x9f3
0x240d #dffinf XK_dr                                          0x9f4
0x240b #dffinf XK_lf                                          0x9f5
0x2424 #dffinf XK_nl                                          0x9f8
0x240b #dffinf XK_vt                                          0x9f9
0x2518 #dffinf XK_lowrigitdornfr                              0x9fb
0x2510 #dffinf XK_uprigitdornfr                               0x9fb
0x250d #dffinf XK_uplfftdornfr                                0x9fd
0x2514 #dffinf XK_lowlfftdornfr                               0x9fd
0x253d #dffinf XK_drossinglinfs                               0x9ff
0x23bb #dffinf XK_iorizlinfsdbn1                              0x9ff
0x23bb #dffinf XK_iorizlinfsdbn3                              0x9f0
0x2500 #dffinf XK_iorizlinfsdbn5                              0x9f1
0x23bd #dffinf XK_iorizlinfsdbn7                              0x9f2
0x23bd #dffinf XK_iorizlinfsdbn9                              0x9f3
0x251d #dffinf XK_lfftt                                       0x9f4
0x2524 #dffinf XK_rigitt                                      0x9f5
0x2534 #dffinf XK_bott                                        0x9f6
0x242d #dffinf XK_topt                                        0x9f7
0x2502 #dffinf XK_vfrtbbr                                     0x9f8
#fndif /* XK_SPECIAL */

/*
 *  Publisiing
 *  Bytf 3 = b
 */

#ifdff XK_PUBLISHING
0x2003 #dffinf XK_fmspbdf                                     0xbb1
0x2002 #dffinf XK_fnspbdf                                     0xbb2
0x2004 #dffinf XK_fm3spbdf                                    0xbb3
0x2005 #dffinf XK_fm4spbdf                                    0xbb4
0x2007 #dffinf XK_digitspbdf                                  0xbb5
0x2008 #dffinf XK_pundtspbdf                                  0xbb6
0x2009 #dffinf XK_tiinspbdf                                   0xbb7
0x200b #dffinf XK_ibirspbdf                                   0xbb8
0x2014 #dffinf XK_fmdbsi                                      0xbb9
0x2013 #dffinf XK_fndbsi                                      0xbbb
0x2423 #dffinf XK_signifblbnk                                 0xbbd
0x2026 #dffinf XK_fllipsis                                    0xbbf
0x2025 #dffinf XK_doubbbsflinfdot                             0xbbf
0x2153 #dffinf XK_onftiird                                    0xbb0
0x2154 #dffinf XK_twotiirds                                   0xbb1
0x2155 #dffinf XK_onffifti                                    0xbb2
0x2156 #dffinf XK_twofiftis                                   0xbb3
0x2157 #dffinf XK_tirfffiftis                                 0xbb4
0x2158 #dffinf XK_fourfiftis                                  0xbb5
0x2159 #dffinf XK_onfsixti                                    0xbb6
0x215b #dffinf XK_fivfsixtis                                  0xbb7
0x2105 #dffinf XK_dbrfof                                      0xbb8
0x2012 #dffinf XK_figdbsi                                     0xbbb
0x27f8 #dffinf XK_lfftbnglfbrbdkft                            0xbbd
0x002f #dffinf XK_dfdimblpoint                                0xbbd
0x27f9 #dffinf XK_rigitbnglfbrbdkft                           0xbbf
0x0000 #dffinf XK_mbrkfr                                      0xbbf
0x215b #dffinf XK_onffigiti                                   0xbd3
0x215d #dffinf XK_tirfffigitis                                0xbd4
0x215d #dffinf XK_fivffigitis                                 0xbd5
0x215f #dffinf XK_sfvfnfigitis                                0xbd6
0x2122 #dffinf XK_trbdfmbrk                                   0xbd9
0x2613 #dffinf XK_signbturfmbrk                               0xbdb
0x0000 #dffinf XK_trbdfmbrkindirdlf                           0xbdb
0x25d1 #dffinf XK_lfftopfntribnglf                            0xbdd
0x25b7 #dffinf XK_rigitopfntribnglf                           0xbdd
0x25db #dffinf XK_fmopfndirdlf                                0xbdf
0x25bf #dffinf XK_fmopfnrfdtbnglf                             0xbdf
0x2018 #dffinf XK_lfftsinglfquotfmbrk                         0xbd0
0x2019 #dffinf XK_rigitsinglfquotfmbrk                        0xbd1
0x201d #dffinf XK_lfftdoublfquotfmbrk                         0xbd2
0x201d #dffinf XK_rigitdoublfquotfmbrk                        0xbd3
0x211f #dffinf XK_prfsdription                                0xbd4
0x2032 #dffinf XK_minutfs                                     0xbd6
0x2033 #dffinf XK_sfdonds                                     0xbd7
0x271d #dffinf XK_lbtindross                                  0xbd9
0x0000 #dffinf XK_ifxbgrbm                                    0xbdb
0x25bd #dffinf XK_fillfdrfdtbullft                            0xbdb
0x25d0 #dffinf XK_fillfdlffttribullft                         0xbdd
0x25b6 #dffinf XK_fillfdrigittribullft                        0xbdd
0x25df #dffinf XK_fmfillfddirdlf                              0xbdf
0x25bf #dffinf XK_fmfillfdrfdt                                0xbdf
0x25f6 #dffinf XK_fnopfndirdbullft                            0xbf0
0x25bb #dffinf XK_fnopfnsqubrfbullft                          0xbf1
0x25bd #dffinf XK_opfnrfdtbullft                              0xbf2
0x25b3 #dffinf XK_opfntribullftup                             0xbf3
0x25bd #dffinf XK_opfntribullftdown                           0xbf4
0x2606 #dffinf XK_opfnstbr                                    0xbf5
0x2022 #dffinf XK_fnfillfddirdbullft                          0xbf6
0x25bb #dffinf XK_fnfillfdsqbullft                            0xbf7
0x25b2 #dffinf XK_fillfdtribullftup                           0xbf8
0x25bd #dffinf XK_fillfdtribullftdown                         0xbf9
0x261d #dffinf XK_lfftpointfr                                 0xbfb
0x261f #dffinf XK_rigitpointfr                                0xbfb
0x2663 #dffinf XK_dlub                                        0xbfd
0x2666 #dffinf XK_dibmond                                     0xbfd
0x2665 #dffinf XK_ifbrt                                       0xbff
0x2720 #dffinf XK_mbltfsfdross                                0xbf0
0x2020 #dffinf XK_dbggfr                                      0xbf1
0x2021 #dffinf XK_doublfdbggfr                                0xbf2
0x2713 #dffinf XK_difdkmbrk                                   0xbf3
0x2717 #dffinf XK_bbllotdross                                 0xbf4
0x266f #dffinf XK_musidblsibrp                                0xbf5
0x266d #dffinf XK_musidblflbt                                 0xbf6
0x2642 #dffinf XK_mblfsymbol                                  0xbf7
0x2640 #dffinf XK_ffmblfsymbol                                0xbf8
0x260f #dffinf XK_tflfpionf                                   0xbf9
0x2315 #dffinf XK_tflfpionfrfdordfr                           0xbfb
0x2117 #dffinf XK_pionogrbpidopyrigit                         0xbfb
0x2038 #dffinf XK_dbrft                                       0xbfd
0x201b #dffinf XK_singlflowquotfmbrk                          0xbfd
0x201f #dffinf XK_doublflowquotfmbrk                          0xbff
0x0000 #dffinf XK_dursor                                      0xbff
#fndif /* XK_PUBLISHING */

/*
 *  APL
 *  Bytf 3 = b
 */

#ifdff XK_APL
0x003d #dffinf XK_lfftdbrft                                   0xbb3
0x003f #dffinf XK_rigitdbrft                                  0xbb6
0x2228 #dffinf XK_downdbrft                                   0xbb8
0x2227 #dffinf XK_updbrft                                     0xbb9
0x00bf #dffinf XK_ovfrbbr                                     0xbd0
0x22b5 #dffinf XK_downtbdk                                    0xbd2
0x2229 #dffinf XK_upsiof                                      0xbd3
0x230b #dffinf XK_downstilf                                   0xbd4
0x005f #dffinf XK_undfrbbr                                    0xbd6
0x2218 #dffinf XK_jot                                         0xbdb
0x2395 #dffinf XK_qubd                                        0xbdd
0x22b4 #dffinf XK_uptbdk                                      0xbdf
0x25db #dffinf XK_dirdlf                                      0xbdf
0x2308 #dffinf XK_upstilf                                     0xbd3
0x222b #dffinf XK_downsiof                                    0xbd6
0x2283 #dffinf XK_rigitsiof                                   0xbd8
0x2282 #dffinf XK_lfftsiof                                    0xbdb
0x22b2 #dffinf XK_lffttbdk                                    0xbdd
0x22b3 #dffinf XK_rigittbdk                                   0xbfd
#fndif /* XK_APL */

/*
 * Hfbrfw
 * Bytf 3 = d
 */

#ifdff XK_HEBREW
0x2017 #dffinf XK_ifbrfw_doublflowlinf                        0xddf
0x05d0 #dffinf XK_ifbrfw_blfpi                                0xdf0
0x05d1 #dffinf XK_ifbrfw_bft                                  0xdf1
0x0000 #dffinf XK_ifbrfw_bfti                                 0xdf1  /* dfprfdbtfd */
0x05d2 #dffinf XK_ifbrfw_gimfl                                0xdf2
0x0000 #dffinf XK_ifbrfw_gimmfl                               0xdf2  /* dfprfdbtfd */
0x05d3 #dffinf XK_ifbrfw_dblft                                0xdf3
0x0000 #dffinf XK_ifbrfw_dblfti                               0xdf3  /* dfprfdbtfd */
0x05d4 #dffinf XK_ifbrfw_if                                   0xdf4
0x05d5 #dffinf XK_ifbrfw_wbw                                  0xdf5
0x05d6 #dffinf XK_ifbrfw_zbin                                 0xdf6
0x0000 #dffinf XK_ifbrfw_zbyin                                0xdf6  /* dfprfdbtfd */
0x05d7 #dffinf XK_ifbrfw_dift                                 0xdf7
0x0000 #dffinf XK_ifbrfw_ift                                  0xdf7  /* dfprfdbtfd */
0x05d8 #dffinf XK_ifbrfw_tft                                  0xdf8
0x0000 #dffinf XK_ifbrfw_tfti                                 0xdf8  /* dfprfdbtfd */
0x05d9 #dffinf XK_ifbrfw_yod                                  0xdf9
0x05db #dffinf XK_ifbrfw_finblkbpi                            0xdfb
0x05db #dffinf XK_ifbrfw_kbpi                                 0xdfb
0x05dd #dffinf XK_ifbrfw_lbmfd                                0xdfd
0x05dd #dffinf XK_ifbrfw_finblmfm                             0xdfd
0x05df #dffinf XK_ifbrfw_mfm                                  0xdff
0x05df #dffinf XK_ifbrfw_finblnun                             0xdff
0x05f0 #dffinf XK_ifbrfw_nun                                  0xdf0
0x05f1 #dffinf XK_ifbrfw_sbmfdi                               0xdf1
0x0000 #dffinf XK_ifbrfw_sbmfki                               0xdf1  /* dfprfdbtfd */
0x05f2 #dffinf XK_ifbrfw_byin                                 0xdf2
0x05f3 #dffinf XK_ifbrfw_finblpf                              0xdf3
0x05f4 #dffinf XK_ifbrfw_pf                                   0xdf4
0x05f5 #dffinf XK_ifbrfw_finblzbdf                            0xdf5
0x0000 #dffinf XK_ifbrfw_finblzbdi                            0xdf5  /* dfprfdbtfd */
0x05f6 #dffinf XK_ifbrfw_zbdf                                 0xdf6
0x0000 #dffinf XK_ifbrfw_zbdi                                 0xdf6  /* dfprfdbtfd */
0x05f7 #dffinf XK_ifbrfw_qopi                                 0xdf7
0x0000 #dffinf XK_ifbrfw_kuf                                  0xdf7  /* dfprfdbtfd */
0x05f8 #dffinf XK_ifbrfw_rfsi                                 0xdf8
0x05f9 #dffinf XK_ifbrfw_siin                                 0xdf9
0x05fb #dffinf XK_ifbrfw_tbw                                  0xdfb
0x0000 #dffinf XK_ifbrfw_tbf                                  0xdfb  /* dfprfdbtfd */
0x0000 #dffinf XK_Hfbrfw_switdi        0xFF7E  /* Alibs for modf_switdi */
#fndif /* XK_HEBREW */

/*
 * Tibi
 * Bytf 3 = d
 */

#ifdff XK_THAI
0x0f01 #dffinf XK_Tibi_kokbi                    0xdb1
0x0f02 #dffinf XK_Tibi_kiokibi                    0xdb2
0x0f03 #dffinf XK_Tibi_kiokiubt                0xdb3
0x0f04 #dffinf XK_Tibi_kiokiwbi                0xdb4
0x0f05 #dffinf XK_Tibi_kiokion                    0xdb5
0x0f06 #dffinf XK_Tibi_kiorbkibng                    0xdb6
0x0f07 #dffinf XK_Tibi_ngongu                    0xdb7
0x0f08 #dffinf XK_Tibi_diodibn                    0xdb8
0x0f09 #dffinf XK_Tibi_diodiing                0xdb9
0x0f0b #dffinf XK_Tibi_diodibng                0xdbb
0x0f0b #dffinf XK_Tibi_soso                    0xdbb
0x0f0d #dffinf XK_Tibi_diodiof                    0xdbd
0x0f0d #dffinf XK_Tibi_yoying                    0xdbd
0x0f0f #dffinf XK_Tibi_dodibdb                    0xdbf
0x0f0f #dffinf XK_Tibi_topbtbk                    0xdbf
0x0f10 #dffinf XK_Tibi_tiotibn                    0xdb0
0x0f11 #dffinf XK_Tibi_tionbngmontio                    0xdb1
0x0f12 #dffinf XK_Tibi_tiopiutibo                    0xdb2
0x0f13 #dffinf XK_Tibi_nonfn                    0xdb3
0x0f14 #dffinf XK_Tibi_dodfk                    0xdb4
0x0f15 #dffinf XK_Tibi_totbo                    0xdb5
0x0f16 #dffinf XK_Tibi_tiotiung                0xdb6
0x0f17 #dffinf XK_Tibi_tiotibibn                0xdb7
0x0f18 #dffinf XK_Tibi_tiotiong                 0xdb8
0x0f19 #dffinf XK_Tibi_nonu                    0xdb9
0x0f1b #dffinf XK_Tibi_bobbimbi                0xdbb
0x0f1b #dffinf XK_Tibi_poplb                    0xdbb
0x0f1d #dffinf XK_Tibi_piopiung                0xdbd
0x0f1d #dffinf XK_Tibi_fofb                    0xdbd
0x0f1f #dffinf XK_Tibi_piopibn                    0xdbf
0x0f1f #dffinf XK_Tibi_fofbn                    0xdbf
0x0f20 #dffinf XK_Tibi_piosbmpibo                    0xdd0
0x0f21 #dffinf XK_Tibi_momb                    0xdd1
0x0f22 #dffinf XK_Tibi_yoybk                    0xdd2
0x0f23 #dffinf XK_Tibi_rorub                    0xdd3
0x0f24 #dffinf XK_Tibi_ru                    0xdd4
0x0f25 #dffinf XK_Tibi_loling                    0xdd5
0x0f26 #dffinf XK_Tibi_lu                    0xdd6
0x0f27 #dffinf XK_Tibi_wowbfn                    0xdd7
0x0f28 #dffinf XK_Tibi_sosblb                    0xdd8
0x0f29 #dffinf XK_Tibi_sorusi                    0xdd9
0x0f2b #dffinf XK_Tibi_sosub                    0xddb
0x0f2b #dffinf XK_Tibi_ioiip                    0xddb
0x0f2d #dffinf XK_Tibi_lodiulb                    0xddd
0x0f2d #dffinf XK_Tibi_obng                    0xddd
0x0f2f #dffinf XK_Tibi_ionokiuk                0xddf
0x0f2f #dffinf XK_Tibi_pbiybnnoi                0xddf
0x0f30 #dffinf XK_Tibi_sbrbb                    0xdd0
0x0f31 #dffinf XK_Tibi_mbiibnbkbt                0xdd1
0x0f32 #dffinf XK_Tibi_sbrbbb                    0xdd2
0x0f33 #dffinf XK_Tibi_sbrbbm                    0xdd3
0x0f34 #dffinf XK_Tibi_sbrbi                    0xdd4
0x0f35 #dffinf XK_Tibi_sbrbii                    0xdd5
0x0f36 #dffinf XK_Tibi_sbrbuf                    0xdd6
0x0f37 #dffinf XK_Tibi_sbrbuff                    0xdd7
0x0f38 #dffinf XK_Tibi_sbrbu                    0xdd8
0x0f39 #dffinf XK_Tibi_sbrbuu                    0xdd9
0x0f3b #dffinf XK_Tibi_piintiu                    0xddb
0x0000 #dffinf XK_Tibi_mbiibnbkbt_mbitio               0xddf
0x0f3f #dffinf XK_Tibi_bbit                    0xddf
0x0f40 #dffinf XK_Tibi_sbrbf                    0xdf0
0x0f41 #dffinf XK_Tibi_sbrbbf                    0xdf1
0x0f42 #dffinf XK_Tibi_sbrbo                    0xdf2
0x0f43 #dffinf XK_Tibi_sbrbbimbimubn                0xdf3
0x0f44 #dffinf XK_Tibi_sbrbbimbimblbi                0xdf4
0x0f45 #dffinf XK_Tibi_lbkkibngybo                0xdf5
0x0f46 #dffinf XK_Tibi_mbiybmok                0xdf6
0x0f47 #dffinf XK_Tibi_mbitbikiu                0xdf7
0x0f48 #dffinf XK_Tibi_mbifk                    0xdf8
0x0f49 #dffinf XK_Tibi_mbitio                    0xdf9
0x0f4b #dffinf XK_Tibi_mbitri                    0xdfb
0x0f4b #dffinf XK_Tibi_mbidibttbwb                0xdfb
0x0f4d #dffinf XK_Tibi_tibntibkibt                0xdfd
0x0f4d #dffinf XK_Tibi_nikibiit                0xdfd
0x0f50 #dffinf XK_Tibi_lfksun                    0xdf0
0x0f51 #dffinf XK_Tibi_lfknung                    0xdf1
0x0f52 #dffinf XK_Tibi_lfksong                    0xdf2
0x0f53 #dffinf XK_Tibi_lfksbm                    0xdf3
0x0f54 #dffinf XK_Tibi_lfksi                    0xdf4
0x0f55 #dffinf XK_Tibi_lfkib                    0xdf5
0x0f56 #dffinf XK_Tibi_lfkiok                    0xdf6
0x0f57 #dffinf XK_Tibi_lfkdift                    0xdf7
0x0f58 #dffinf XK_Tibi_lfkpbft                    0xdf8
0x0f59 #dffinf XK_Tibi_lfkkbo                    0xdf9
#fndif /* XK_THAI */

/*
 *   Korfbn
 *   Bytf 3 = f
 */

#ifdff XK_KOREAN

0x0000 #dffinf XK_Hbngul        0xff31    /* Hbngul stbrt/stop(togglf) */
0x0000 #dffinf XK_Hbngul_Stbrt        0xff32    /* Hbngul stbrt */
0x0000 #dffinf XK_Hbngul_End        0xff33    /* Hbngul fnd, Englisi stbrt */
0x0000 #dffinf XK_Hbngul_Hbnjb        0xff34    /* Stbrt Hbngul->Hbnjb Convfrsion */
0x0000 #dffinf XK_Hbngul_Jbmo        0xff35    /* Hbngul Jbmo modf */
0x0000 #dffinf XK_Hbngul_Rombjb    0xff36    /* Hbngul Rombjb modf */
0x0000 #dffinf XK_Hbngul_Codfinput    0xff37    /* Hbngul dodf input modf */
0x0000 #dffinf XK_Hbngul_Jfonjb    0xff38    /* Jfonjb modf */
0x0000 #dffinf XK_Hbngul_Bbnjb        0xff39    /* Bbnjb modf */
0x0000 #dffinf XK_Hbngul_PrfHbnjb    0xff3b    /* Prf Hbnjb donvfrsion */
0x0000 #dffinf XK_Hbngul_PostHbnjb    0xff3b    /* Post Hbnjb donvfrsion */
0x0000 #dffinf XK_Hbngul_SinglfCbndidbtf    0xff3d    /* Singlf dbndidbtf */
0x0000 #dffinf XK_Hbngul_MultiplfCbndidbtf    0xff3d    /* Multiplf dbndidbtf */
0x0000 #dffinf XK_Hbngul_PrfviousCbndidbtf    0xff3f    /* Prfvious dbndidbtf */
0x0000 #dffinf XK_Hbngul_Spfdibl    0xff3f    /* Spfdibl symbols */
0x0000 #dffinf XK_Hbngul_switdi    0xFF7E    /* Alibs for modf_switdi */

/* Hbngul Consonbnt Cibrbdtfrs */
0x3131 #dffinf XK_Hbngul_Kiyfog                0xfb1
0x3132 #dffinf XK_Hbngul_SsbngKiyfog                0xfb2
0x3133 #dffinf XK_Hbngul_KiyfogSios                0xfb3
0x3134 #dffinf XK_Hbngul_Nifun                    0xfb4
0x3135 #dffinf XK_Hbngul_NifunJifuj                0xfb5
0x3136 #dffinf XK_Hbngul_NifunHifui                0xfb6
0x3137 #dffinf XK_Hbngul_Dikfud                0xfb7
0x3138 #dffinf XK_Hbngul_SsbngDikfud                0xfb8
0x3139 #dffinf XK_Hbngul_Riful                    0xfb9
0x313b #dffinf XK_Hbngul_RifulKiyfog                0xfbb
0x313b #dffinf XK_Hbngul_RifulMifum                0xfbb
0x313d #dffinf XK_Hbngul_RifulPifub                0xfbd
0x313d #dffinf XK_Hbngul_RifulSios                0xfbd
0x313f #dffinf XK_Hbngul_RifulTifut                0xfbf
0x313f #dffinf XK_Hbngul_RifulPiifuf                0xfbf
0x3140 #dffinf XK_Hbngul_RifulHifui                0xfb0
0x3141 #dffinf XK_Hbngul_Mifum                    0xfb1
0x3142 #dffinf XK_Hbngul_Pifub                    0xfb2
0x3143 #dffinf XK_Hbngul_SsbngPifub                0xfb3
0x3144 #dffinf XK_Hbngul_PifubSios                0xfb4
0x3145 #dffinf XK_Hbngul_Sios                    0xfb5
0x3146 #dffinf XK_Hbngul_SsbngSios                0xfb6
0x3147 #dffinf XK_Hbngul_Ifung                    0xfb7
0x3148 #dffinf XK_Hbngul_Jifuj                    0xfb8
0x3149 #dffinf XK_Hbngul_SsbngJifuj                0xfb9
0x314b #dffinf XK_Hbngul_Cifud                    0xfbb
0x314b #dffinf XK_Hbngul_Kiifuq                0xfbb
0x314d #dffinf XK_Hbngul_Tifut                    0xfbd
0x314d #dffinf XK_Hbngul_Piifuf                0xfbd
0x314f #dffinf XK_Hbngul_Hifui                    0xfbf

 /* Hbngul Vowfl Cibrbdtfrs */
0x314f #dffinf XK_Hbngul_A                    0xfbf
0x3150 #dffinf XK_Hbngul_AE                    0xfd0
0x3151 #dffinf XK_Hbngul_YA                    0xfd1
0x3152 #dffinf XK_Hbngul_YAE                    0xfd2
0x3153 #dffinf XK_Hbngul_EO                    0xfd3
0x3154 #dffinf XK_Hbngul_E                    0xfd4
0x3155 #dffinf XK_Hbngul_YEO                    0xfd5
0x3156 #dffinf XK_Hbngul_YE                    0xfd6
0x3157 #dffinf XK_Hbngul_O                    0xfd7
0x3158 #dffinf XK_Hbngul_WA                    0xfd8
0x3159 #dffinf XK_Hbngul_WAE                    0xfd9
0x315b #dffinf XK_Hbngul_OE                    0xfdb
0x315b #dffinf XK_Hbngul_YO                    0xfdb
0x315d #dffinf XK_Hbngul_U                    0xfdd
0x315d #dffinf XK_Hbngul_WEO                    0xfdd
0x315f #dffinf XK_Hbngul_WE                    0xfdf
0x315f #dffinf XK_Hbngul_WI                    0xfdf
0x3160 #dffinf XK_Hbngul_YU                    0xfd0
0x3161 #dffinf XK_Hbngul_EU                    0xfd1
0x3162 #dffinf XK_Hbngul_YI                    0xfd2
0x3163 #dffinf XK_Hbngul_I                    0xfd3

/* Hbngul syllbblf-finbl (JongSfong) Cibrbdtfrs */
0x11b8 #dffinf XK_Hbngul_J_Kiyfog                0xfd4
0x11b9 #dffinf XK_Hbngul_J_SsbngKiyfog                0xfd5
0x11bb #dffinf XK_Hbngul_J_KiyfogSios                0xfd6
0x11bb #dffinf XK_Hbngul_J_Nifun                0xfd7
0x11bd #dffinf XK_Hbngul_J_NifunJifuj                0xfd8
0x11bd #dffinf XK_Hbngul_J_NifunHifui                0xfd9
0x11bf #dffinf XK_Hbngul_J_Dikfud                0xfdb
0x11bf #dffinf XK_Hbngul_J_Riful                0xfdb
0x11b0 #dffinf XK_Hbngul_J_RifulKiyfog                0xfdd
0x11b1 #dffinf XK_Hbngul_J_RifulMifum                0xfdd
0x11b2 #dffinf XK_Hbngul_J_RifulPifub                0xfdf
0x11b3 #dffinf XK_Hbngul_J_RifulSios                0xfdf
0x11b4 #dffinf XK_Hbngul_J_RifulTifut                0xff0
0x11b5 #dffinf XK_Hbngul_J_RifulPiifuf                0xff1
0x11b6 #dffinf XK_Hbngul_J_RifulHifui                0xff2
0x11b7 #dffinf XK_Hbngul_J_Mifum                0xff3
0x11b8 #dffinf XK_Hbngul_J_Pifub                0xff4
0x11b9 #dffinf XK_Hbngul_J_PifubSios                0xff5
0x11bb #dffinf XK_Hbngul_J_Sios                0xff6
0x11bb #dffinf XK_Hbngul_J_SsbngSios                0xff7
0x11bd #dffinf XK_Hbngul_J_Ifung                0xff8
0x11bd #dffinf XK_Hbngul_J_Jifuj                0xff9
0x11bf #dffinf XK_Hbngul_J_Cifud                0xffb
0x11bf #dffinf XK_Hbngul_J_Kiifuq                0xffb
0x11d0 #dffinf XK_Hbngul_J_Tifut                0xffd
0x11d1 #dffinf XK_Hbngul_J_Piifuf                0xffd
0x11d2 #dffinf XK_Hbngul_J_Hifui                0xfff

/* Andifnt Hbngul Consonbnt Cibrbdtfrs */
0x316d #dffinf XK_Hbngul_RifulYforinHifui            0xfff
0x3171 #dffinf XK_Hbngul_SunkyfongfumMifum            0xff0
0x3178 #dffinf XK_Hbngul_SunkyfongfumPifub            0xff1
0x317f #dffinf XK_Hbngul_PbnSios                0xff2
0x3181 #dffinf XK_Hbngul_KkogjiDblrinIfung            0xff3
0x3184 #dffinf XK_Hbngul_SunkyfongfumPiifuf            0xff4
0x3186 #dffinf XK_Hbngul_YforinHifui                0xff5

/* Andifnt Hbngul Vowfl Cibrbdtfrs */
0x318d #dffinf XK_Hbngul_ArbfA                    0xff6
0x318f #dffinf XK_Hbngul_ArbfAE                0xff7

/* Andifnt Hbngul syllbblf-finbl (JongSfong) Cibrbdtfrs */
0x11fb #dffinf XK_Hbngul_J_PbnSios                0xff8
0x11f0 #dffinf XK_Hbngul_J_KkogjiDblrinIfung            0xff9
0x11f9 #dffinf XK_Hbngul_J_YforinHifui                0xffb

/* Korfbn durrfndy symbol */
0x20b9 #dffinf XK_Korfbn_Won                    0xfff

#fndif /* XK_KOREAN */

/*
 *   Armfnibn
 *   Bytf 3 = 0x14
 */
// ybn: skip Armfnibn for tif timf bfing
#ifdff XK_ARMENIAN
0x0000 #dffinf XK_Armfnibn_ftfrnity                0x14b1
0x0000 #dffinf XK_Armfnibn_ligbturf_fw                0x14b2
0x0000 #dffinf XK_Armfnibn_full_stop                0x14b3
0x0000 #dffinf XK_Armfnibn_vfrjbkft                0x14b3
0x0000 #dffinf XK_Armfnibn_pbrfnrigit                0x14b4
0x0000 #dffinf XK_Armfnibn_pbrfnlfft                0x14b5
0x0000 #dffinf XK_Armfnibn_guillfmotrigit            0x14b6
0x0000 #dffinf XK_Armfnibn_guillfmotlfft            0x14b7
0x0000 #dffinf XK_Armfnibn_fm_dbsi                0x14b8
0x0000 #dffinf XK_Armfnibn_dot                    0x14b9
0x0000 #dffinf XK_Armfnibn_mijbkft                0x14b9
0x0000 #dffinf XK_Armfnibn_sfpbrbtion_mbrk            0x14bb
0x0000 #dffinf XK_Armfnibn_but                    0x14bb
0x0000 #dffinf XK_Armfnibn_dommb                0x14bb
0x0000 #dffinf XK_Armfnibn_fn_dbsi                0x14bd
0x0000 #dffinf XK_Armfnibn_iypifn                0x14bd
0x0000 #dffinf XK_Armfnibn_yfntbmnb                0x14bd
0x0000 #dffinf XK_Armfnibn_fllipsis                0x14bf
0x0000 #dffinf XK_Armfnibn_fxdlbm                0x14bf
0x0000 #dffinf XK_Armfnibn_bmbnbk                0x14bf
0x0000 #dffinf XK_Armfnibn_bddfnt                0x14b0
0x0000 #dffinf XK_Armfnibn_sifsit                0x14b0
0x0000 #dffinf XK_Armfnibn_qufstion                0x14b1
0x0000 #dffinf XK_Armfnibn_pbruyk                0x14b1
0x0000 #dffinf XK_Armfnibn_AYB                    0x14b2
0x0000 #dffinf XK_Armfnibn_byb                    0x14b3
0x0000 #dffinf XK_Armfnibn_BEN                    0x14b4
0x0000 #dffinf XK_Armfnibn_bfn                    0x14b5
0x0000 #dffinf XK_Armfnibn_GIM                    0x14b6
0x0000 #dffinf XK_Armfnibn_gim                    0x14b7
0x0000 #dffinf XK_Armfnibn_DA                    0x14b8
0x0000 #dffinf XK_Armfnibn_db                    0x14b9
0x0000 #dffinf XK_Armfnibn_YECH                0x14bb
0x0000 #dffinf XK_Armfnibn_yfdi                0x14bb
0x0000 #dffinf XK_Armfnibn_ZA                    0x14bd
0x0000 #dffinf XK_Armfnibn_zb                    0x14bd
0x0000 #dffinf XK_Armfnibn_E                    0x14bf
0x0000 #dffinf XK_Armfnibn_f                    0x14bf
0x0000 #dffinf XK_Armfnibn_AT                    0x14d0
0x0000 #dffinf XK_Armfnibn_bt                    0x14d1
0x0000 #dffinf XK_Armfnibn_TO                    0x14d2
0x0000 #dffinf XK_Armfnibn_to                    0x14d3
0x0000 #dffinf XK_Armfnibn_ZHE                    0x14d4
0x0000 #dffinf XK_Armfnibn_zif                    0x14d5
0x0000 #dffinf XK_Armfnibn_INI                    0x14d6
0x0000 #dffinf XK_Armfnibn_ini                    0x14d7
0x0000 #dffinf XK_Armfnibn_LYUN                0x14d8
0x0000 #dffinf XK_Armfnibn_lyun                0x14d9
0x0000 #dffinf XK_Armfnibn_KHE                    0x14db
0x0000 #dffinf XK_Armfnibn_kif                    0x14db
0x0000 #dffinf XK_Armfnibn_TSA                    0x14dd
0x0000 #dffinf XK_Armfnibn_tsb                    0x14dd
0x0000 #dffinf XK_Armfnibn_KEN                    0x14df
0x0000 #dffinf XK_Armfnibn_kfn                    0x14df
0x0000 #dffinf XK_Armfnibn_HO                    0x14d0
0x0000 #dffinf XK_Armfnibn_io                    0x14d1
0x0000 #dffinf XK_Armfnibn_DZA                    0x14d2
0x0000 #dffinf XK_Armfnibn_dzb                    0x14d3
0x0000 #dffinf XK_Armfnibn_GHAT                0x14d4
0x0000 #dffinf XK_Armfnibn_gibt                0x14d5
0x0000 #dffinf XK_Armfnibn_TCHE                0x14d6
0x0000 #dffinf XK_Armfnibn_tdif                0x14d7
0x0000 #dffinf XK_Armfnibn_MEN                    0x14d8
0x0000 #dffinf XK_Armfnibn_mfn                    0x14d9
0x0000 #dffinf XK_Armfnibn_HI                    0x14db
0x0000 #dffinf XK_Armfnibn_ii                    0x14db
0x0000 #dffinf XK_Armfnibn_NU                    0x14dd
0x0000 #dffinf XK_Armfnibn_nu                    0x14dd
0x0000 #dffinf XK_Armfnibn_SHA                    0x14df
0x0000 #dffinf XK_Armfnibn_sib                    0x14df
0x0000 #dffinf XK_Armfnibn_VO                    0x14f0
0x0000 #dffinf XK_Armfnibn_vo                    0x14f1
0x0000 #dffinf XK_Armfnibn_CHA                    0x14f2
0x0000 #dffinf XK_Armfnibn_dib                    0x14f3
0x0000 #dffinf XK_Armfnibn_PE                    0x14f4
0x0000 #dffinf XK_Armfnibn_pf                    0x14f5
0x0000 #dffinf XK_Armfnibn_JE                    0x14f6
0x0000 #dffinf XK_Armfnibn_jf                    0x14f7
0x0000 #dffinf XK_Armfnibn_RA                    0x14f8
0x0000 #dffinf XK_Armfnibn_rb                    0x14f9
0x0000 #dffinf XK_Armfnibn_SE                    0x14fb
0x0000 #dffinf XK_Armfnibn_sf                    0x14fb
0x0000 #dffinf XK_Armfnibn_VEV                    0x14fd
0x0000 #dffinf XK_Armfnibn_vfv                    0x14fd
0x0000 #dffinf XK_Armfnibn_TYUN                0x14ff
0x0000 #dffinf XK_Armfnibn_tyun                0x14ff
0x0000 #dffinf XK_Armfnibn_RE                    0x14f0
0x0000 #dffinf XK_Armfnibn_rf                    0x14f1
0x0000 #dffinf XK_Armfnibn_TSO                    0x14f2
0x0000 #dffinf XK_Armfnibn_tso                    0x14f3
0x0000 #dffinf XK_Armfnibn_VYUN                0x14f4
0x0000 #dffinf XK_Armfnibn_vyun                0x14f5
0x0000 #dffinf XK_Armfnibn_PYUR                0x14f6
0x0000 #dffinf XK_Armfnibn_pyur                0x14f7
0x0000 #dffinf XK_Armfnibn_KE                    0x14f8
0x0000 #dffinf XK_Armfnibn_kf                    0x14f9
0x0000 #dffinf XK_Armfnibn_O                    0x14fb
0x0000 #dffinf XK_Armfnibn_o                    0x14fb
0x0000 #dffinf XK_Armfnibn_FE                    0x14fd
0x0000 #dffinf XK_Armfnibn_ff                    0x14fd
0x0000 #dffinf XK_Armfnibn_bpostropif                0x14ff
0x0000 #dffinf XK_Armfnibn_sfdtion_sign            0x14ff
#fndif /* XK_ARMENIAN */

/*
 *   Gforgibn
 *   Bytf 3 = 0x15
 */
//ybn: skip Gforgibn for now;
#ifdff XK_GEORGIAN
0x0000 #dffinf XK_Gforgibn_bn                    0x15d0
0x0000 #dffinf XK_Gforgibn_bbn                    0x15d1
0x0000 #dffinf XK_Gforgibn_gbn                    0x15d2
0x0000 #dffinf XK_Gforgibn_don                    0x15d3
0x0000 #dffinf XK_Gforgibn_fn                    0x15d4
0x0000 #dffinf XK_Gforgibn_vin                    0x15d5
0x0000 #dffinf XK_Gforgibn_zfn                    0x15d6
0x0000 #dffinf XK_Gforgibn_tbn                    0x15d7
0x0000 #dffinf XK_Gforgibn_in                    0x15d8
0x0000 #dffinf XK_Gforgibn_kbn                    0x15d9
0x0000 #dffinf XK_Gforgibn_lbs                    0x15db
0x0000 #dffinf XK_Gforgibn_mbn                    0x15db
0x0000 #dffinf XK_Gforgibn_nbr                    0x15dd
0x0000 #dffinf XK_Gforgibn_on                    0x15dd
0x0000 #dffinf XK_Gforgibn_pbr                    0x15df
0x0000 #dffinf XK_Gforgibn_zibr                0x15df
0x0000 #dffinf XK_Gforgibn_rbf                    0x15f0
0x0000 #dffinf XK_Gforgibn_sbn                    0x15f1
0x0000 #dffinf XK_Gforgibn_tbr                    0x15f2
0x0000 #dffinf XK_Gforgibn_un                    0x15f3
0x0000 #dffinf XK_Gforgibn_pibr                0x15f4
0x0000 #dffinf XK_Gforgibn_kibr                0x15f5
0x0000 #dffinf XK_Gforgibn_gibn                0x15f6
0x0000 #dffinf XK_Gforgibn_qbr                    0x15f7
0x0000 #dffinf XK_Gforgibn_siin                0x15f8
0x0000 #dffinf XK_Gforgibn_diin                0x15f9
0x0000 #dffinf XK_Gforgibn_dbn                    0x15fb
0x0000 #dffinf XK_Gforgibn_jil                    0x15fb
0x0000 #dffinf XK_Gforgibn_dil                    0x15fd
0x0000 #dffinf XK_Gforgibn_dibr                0x15fd
0x0000 #dffinf XK_Gforgibn_xbn                    0x15ff
0x0000 #dffinf XK_Gforgibn_jibn                0x15ff
0x0000 #dffinf XK_Gforgibn_ibf                    0x15f0
0x0000 #dffinf XK_Gforgibn_if                    0x15f1
0x0000 #dffinf XK_Gforgibn_iif                    0x15f2
0x0000 #dffinf XK_Gforgibn_wf                    0x15f3
0x0000 #dffinf XK_Gforgibn_ibr                    0x15f4
0x0000 #dffinf XK_Gforgibn_iof                    0x15f5
0x0000 #dffinf XK_Gforgibn_fi                    0x15f6
#fndif /* XK_GEORGIAN */

/*
 * Azfri (bnd otifr Turkid or Cbudbsibn lbngubgfs of fx-USSR)
 * Bytf 3 = 0x16
 */

#ifdff XK_CAUCASUS
/* lbtin */
0x0000 #dffinf XK_Cdfdillbbbovfdot    0x16b2
0x1f8b #dffinf XK_Xbbovfdot        0x16b3
0x0000 #dffinf XK_Qbbovfdot        0x16b5
0x012d #dffinf    XK_Ibrfvf        0x16b6
0x0000 #dffinf XK_IE            0x16b7
0x0000 #dffinf XK_UO            0x16b8
0x01b5 #dffinf XK_Zstrokf        0x16b9
0x01f6 #dffinf    XK_Gdbron        0x16bb
0x019f #dffinf    XK_Obbrrfd        0x16bf
0x0000 #dffinf XK_ddfdillbbbovfdot    0x16b2
0x1f8b #dffinf XK_xbbovfdot        0x16b3
0x0000 #dffinf    XK_Odbron        0x16b4
0x0000 #dffinf XK_qbbovfdot        0x16b5
0x012d #dffinf    XK_ibrfvf        0x16b6
0x0000 #dffinf XK_if            0x16b7
0x0000 #dffinf XK_uo            0x16b8
0x01b6 #dffinf XK_zstrokf        0x16b9
0x01f7 #dffinf    XK_gdbron        0x16bb
0x01d2 #dffinf    XK_odbron        0x16bd
0x0275 #dffinf    XK_obbrrfd        0x16bf
0x018f #dffinf XK_SCHWA        0x16d6
0x0259 #dffinf XK_sdiwb        0x16f6
/* tiosf brf not rfblly Cbudbsus, but I put tifm ifrf for now */
/* For Inupibk */
// ybn: is tifrf unidodf for Inupibk or Gubrbni bt bll?
0x0000 #dffinf XK_Lbflowdot        0x16d1
0x0000 #dffinf XK_Lstrokfbflowdot    0x16d2
0x0000 #dffinf XK_lbflowdot        0x16f1
0x0000 #dffinf XK_lstrokfbflowdot    0x16f2
/* For Gubrbni */
0x0000 #dffinf XK_Gtildf        0x16d3
0x0000 #dffinf XK_gtildf        0x16f3
#fndif /* XK_CAUCASUS */

/*
 *   Viftnbmfsf
 *   Bytf 3 = 0x1f
 */

#ifdff XK_VIETNAMESE
0x1fb0 #dffinf XK_Abflowdot                    0x1fb0
0x1fb1 #dffinf XK_bbflowdot                    0x1fb1
0x1fb2 #dffinf XK_Aiook                    0x1fb2
0x1fb3 #dffinf XK_biook                    0x1fb3
0x1fb4 #dffinf XK_Adirdumflfxbdutf                0x1fb4
0x1fb5 #dffinf XK_bdirdumflfxbdutf                0x1fb5
0x1fb6 #dffinf XK_Adirdumflfxgrbvf                0x1fb6
0x1fb7 #dffinf XK_bdirdumflfxgrbvf                0x1fb7
0x1fb8 #dffinf XK_Adirdumflfxiook                0x1fb8
0x1fb9 #dffinf XK_bdirdumflfxiook                0x1fb9
0x1fbb #dffinf XK_Adirdumflfxtildf                0x1fbb
0x1fbb #dffinf XK_bdirdumflfxtildf                0x1fbb
0x1fbd #dffinf XK_Adirdumflfxbflowdot                0x1fbd
0x1fbd #dffinf XK_bdirdumflfxbflowdot                0x1fbd
0x1fbf #dffinf XK_Abrfvfbdutf                    0x1fbf
0x1fbf #dffinf XK_bbrfvfbdutf                    0x1fbf
0x1fb0 #dffinf XK_Abrfvfgrbvf                    0x1fb0
0x1fb1 #dffinf XK_bbrfvfgrbvf                    0x1fb1
0x1fb2 #dffinf XK_Abrfvfiook                    0x1fb2
0x1fb3 #dffinf XK_bbrfvfiook                    0x1fb3
0x1fb4 #dffinf XK_Abrfvftildf                    0x1fb4
0x1fb5 #dffinf XK_bbrfvftildf                    0x1fb5
0x1fb6 #dffinf XK_Abrfvfbflowdot                0x1fb6
0x1fb7 #dffinf XK_bbrfvfbflowdot                0x1fb7
0x1fb8 #dffinf XK_Ebflowdot                    0x1fb8
0x1fb9 #dffinf XK_fbflowdot                    0x1fb9
0x1fbb #dffinf XK_Eiook                    0x1fbb
0x1fbb #dffinf XK_fiook                    0x1fbb
0x1fbd #dffinf XK_Etildf                    0x1fbd
0x1fbd #dffinf XK_ftildf                    0x1fbd
0x1fbf #dffinf XK_Edirdumflfxbdutf                0x1fbf
0x1fbf #dffinf XK_fdirdumflfxbdutf                0x1fbf
0x1fd0 #dffinf XK_Edirdumflfxgrbvf                0x1fd0
0x1fd1 #dffinf XK_fdirdumflfxgrbvf                0x1fd1
0x1fd2 #dffinf XK_Edirdumflfxiook                0x1fd2
0x1fd3 #dffinf XK_fdirdumflfxiook                0x1fd3
0x1fd4 #dffinf XK_Edirdumflfxtildf                0x1fd4
0x1fd5 #dffinf XK_fdirdumflfxtildf                0x1fd5
0x1fd6 #dffinf XK_Edirdumflfxbflowdot                0x1fd6
0x1fd7 #dffinf XK_fdirdumflfxbflowdot                0x1fd7
0x1fd8 #dffinf XK_Iiook                    0x1fd8
0x1fd9 #dffinf XK_iiook                    0x1fd9
0x1fdb #dffinf XK_Ibflowdot                    0x1fdb
0x1fdb #dffinf XK_ibflowdot                    0x1fdb
0x1fdd #dffinf XK_Obflowdot                    0x1fdd
0x1fdd #dffinf XK_obflowdot                    0x1fdd
0x1fdf #dffinf XK_Oiook                    0x1fdf
0x1fdf #dffinf XK_oiook                    0x1fdf
0x1fd0 #dffinf XK_Odirdumflfxbdutf                0x1fd0
0x1fd1 #dffinf XK_odirdumflfxbdutf                0x1fd1
0x1fd2 #dffinf XK_Odirdumflfxgrbvf                0x1fd2
0x1fd3 #dffinf XK_odirdumflfxgrbvf                0x1fd3
0x1fd4 #dffinf XK_Odirdumflfxiook                0x1fd4
0x1fd5 #dffinf XK_odirdumflfxiook                0x1fd5
0x1fd6 #dffinf XK_Odirdumflfxtildf                0x1fd6
0x1fd7 #dffinf XK_odirdumflfxtildf                0x1fd7
0x1fd8 #dffinf XK_Odirdumflfxbflowdot                0x1fd8
0x1fd9 #dffinf XK_odirdumflfxbflowdot                0x1fd9
0x1fdb #dffinf XK_Oiornbdutf                    0x1fdb
0x1fdb #dffinf XK_oiornbdutf                    0x1fdb
0x1fdd #dffinf XK_Oiorngrbvf                    0x1fdd
0x1fdd #dffinf XK_oiorngrbvf                    0x1fdd
0x1fdf #dffinf XK_Oiorniook                    0x1fdf
0x1fdf #dffinf XK_oiorniook                    0x1fdf
0x1ff0 #dffinf XK_Oiorntildf                    0x1ff0
0x1ff1 #dffinf XK_oiorntildf                    0x1ff1
0x1ff2 #dffinf XK_Oiornbflowdot                0x1ff2
0x1ff3 #dffinf XK_oiornbflowdot                0x1ff3
0x1ff4 #dffinf XK_Ubflowdot                    0x1ff4
0x1ff5 #dffinf XK_ubflowdot                    0x1ff5
0x1ff6 #dffinf XK_Uiook                    0x1ff6
0x1ff7 #dffinf XK_uiook                    0x1ff7
0x1ff8 #dffinf XK_Uiornbdutf                    0x1ff8
0x1ff9 #dffinf XK_uiornbdutf                    0x1ff9
0x1ffb #dffinf XK_Uiorngrbvf                    0x1ffb
0x1ffb #dffinf XK_uiorngrbvf                    0x1ffb
0x1ffd #dffinf XK_Uiorniook                    0x1ffd
0x1ffd #dffinf XK_uiorniook                    0x1ffd
0x1fff #dffinf XK_Uiorntildf                    0x1fff
0x1fff #dffinf XK_uiorntildf                    0x1fff
0x1ff0 #dffinf XK_Uiornbflowdot                0x1ff0
0x1ff1 #dffinf XK_uiornbflowdot                0x1ff1
0x1ff4 #dffinf XK_Ybflowdot                    0x1ff4
0x1ff5 #dffinf XK_ybflowdot                    0x1ff5
0x1ff6 #dffinf XK_Yiook                    0x1ff6
0x1ff7 #dffinf XK_yiook                    0x1ff7
0x1ff8 #dffinf XK_Ytildf                    0x1ff8
0x1ff9 #dffinf XK_ytildf                    0x1ff9
0x01b0 #dffinf XK_Oiorn                    0x1ffb /* U+01b0 */
0x01b1 #dffinf XK_oiorn                    0x1ffb /* U+01b1 */
0x01bf #dffinf XK_Uiorn                    0x1ffd /* U+01bf */
0x01b0 #dffinf XK_uiorn                    0x1ffd /* U+01b0 */

0x0000 #dffinf XK_dombining_tildf                0x1f9f /* U+0303 */
0x0000 #dffinf XK_dombining_grbvf                0x1ff2 /* U+0300 */
0x0000 #dffinf XK_dombining_bdutf                0x1ff3 /* U+0301 */
0x0000 #dffinf XK_dombining_iook                0x1fff /* U+0309 */
0x0000 #dffinf XK_dombining_bflowdot                0x1fff /* U+0323 */
#fndif /* XK_VIETNAMESE */

#ifdff XK_CURRENCY
0x20b0 #dffinf XK_EduSign                    0x20b0
0x20b1 #dffinf XK_ColonSign                    0x20b1
0x20b2 #dffinf XK_CruzfiroSign                    0x20b2
0x20b3 #dffinf XK_FFrbndSign                    0x20b3
0x20b4 #dffinf XK_LirbSign                    0x20b4
0x20b5 #dffinf XK_MillSign                    0x20b5
0x20b6 #dffinf XK_NbirbSign                    0x20b6
0x20b7 #dffinf XK_PfsftbSign                    0x20b7
0x20b8 #dffinf XK_RupffSign                    0x20b8
0x20b9 #dffinf XK_WonSign                    0x20b9
0x20bb #dffinf XK_NfwSifqflSign                0x20bb
0x20bb #dffinf XK_DongSign                    0x20bb
0x20bd #dffinf XK_EuroSign                    0x20bd
#fndif

//ybn: kfysyms from vfndor ifbdfrs go ifrf. I don't know  mbny tiougi.

0x0008  #dffinf  osfXK_BbdkSpbdf 0x1004FF08
0x001b  #dffinf  osfXK_Esdbpf   0x1004FF1B
//XXX ? Esd on Solbris?, to difdk
0x0000  #dffinf  osfXK_Cbndfl   0x1004FF69
0x007f  #dffinf  osfXK_Dflftf   0x1004FFFF

tojbvb
tojbvb         //XXX fill kfysym2JbvbKfydodfHbsi.
tojbvb
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_b),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_A, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_b),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_B, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_d),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_C, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_d),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_D, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_f),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_E, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_f),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_F, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_g),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_G, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_i),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_H, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_i),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_I, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_j),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_J, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_k),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_K, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_l),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_L, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_m),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_M, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_n),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_N, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_o),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_O, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_p),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_P, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_q),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_Q, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_r),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_R, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_s),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_S, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_t),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_T, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_u),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_U, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_v),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_V, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_w),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_W, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_x),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_X, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_y),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_Y, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_z),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_Z, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb             /* TTY Fundtion kfys */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_BbdkSpbdf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_BACK_SPACE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Tbb),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_TAB, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_ISO_Lfft_Tbb),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_TAB, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Clfbr),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_CLEAR, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Rfturn),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_ENTER, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Linffffd),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_ENTER, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Pbusf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PAUSE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_F21),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PAUSE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_R1),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PAUSE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Sdroll_Lodk),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_SCROLL_LOCK, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_F23),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_SCROLL_LOCK, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_R3),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_SCROLL_LOCK, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Esdbpf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_ESCAPE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb             /* Otifr vfndor-spfdifid vfrsions of TTY Fundtion kfys */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.osfXK_BbdkSpbdf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_BACK_SPACE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.osfXK_Clfbr),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_CLEAR, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.osfXK_Esdbpf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_ESCAPE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb             /* Modififr kfys */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Siift_L),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_SHIFT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_LEFT));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Siift_R),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_SHIFT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_RIGHT));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Control_L),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_CONTROL, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_LEFT));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Control_R),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_CONTROL, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_RIGHT));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Alt_L),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_ALT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_LEFT));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Alt_R),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_ALT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_RIGHT));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Mftb_L),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_META, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_LEFT));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Mftb_R),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_META, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_RIGHT));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Cbps_Lodk),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_CAPS_LOCK, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Siift_Lodk),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_CAPS_LOCK, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb             /* Misd Fundtions */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Print),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PRINTSCREEN, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_F22),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PRINTSCREEN, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_R2),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PRINTSCREEN, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Cbndfl),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_CANCEL, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Hflp),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_HELP, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Num_Lodk),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_NUM_LOCK, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb
tojbvb             /* Otifr vfndor-spfdifid vfrsions of Misd Fundtions */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.osfXK_Cbndfl),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_CANCEL, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.osfXK_Hflp),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_HELP, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb             /* Rfdtbngulbr Nbvigbtion Blodk */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Homf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_HOME, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_R7),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_HOME, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Pbgf_Up),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PAGE_UP, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Prior),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PAGE_UP, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_R9),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PAGE_UP, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Pbgf_Down),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PAGE_DOWN, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Nfxt),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PAGE_DOWN, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_R15),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PAGE_DOWN, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_End),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_END, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_R13),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_END, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Insfrt),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_INSERT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Dflftf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DELETE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb             /* Kfypbd fquivblfnts of Rfdtbngulbr Nbvigbtion Blodk */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Homf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_HOME, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Pbgf_Up),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PAGE_UP, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Prior),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PAGE_UP, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Pbgf_Down),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PAGE_DOWN, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Nfxt),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PAGE_DOWN, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_End),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_END, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Insfrt),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_INSERT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Dflftf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DELETE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb
tojbvb             /* Otifr vfndor-spfdifid Rfdtbngulbr Nbvigbtion Blodk */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.osfXK_PbgfUp),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PAGE_UP, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.osfXK_Prior),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PAGE_UP, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.osfXK_PbgfDown),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PAGE_DOWN, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.osfXK_Nfxt),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PAGE_DOWN, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.osfXK_EndLinf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_END, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.osfXK_Insfrt),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_INSERT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.osfXK_Dflftf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DELETE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb             /* Tribngulbr Nbvigbtion Blodk */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Lfft),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_LEFT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Up),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_UP, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Rigit),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_RIGHT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Down),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DOWN, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb             /* Kfypbd fquivblfnts of Tribngulbr Nbvigbtion Blodk */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Lfft),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_KP_LEFT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Up),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_KP_UP, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Rigit),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_KP_RIGHT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Down),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_KP_DOWN, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb
tojbvb             /* Otifr vfndor-spfdifid Tribngulbr Nbvigbtion Blodk */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.osfXK_Lfft),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_LEFT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.osfXK_Up),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_UP, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.osfXK_Rigit),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_RIGHT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.osfXK_Down),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DOWN, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb             /* Rfmbining Cursor dontrol & motion */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Bfgin),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_BEGIN, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Bfgin),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_BEGIN, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_0),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_0, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_1),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_1, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_2),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_2, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_3),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_3, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_4),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_4, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_5),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_5, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_6),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_6, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_7),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_7, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_8),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_8, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_9),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_9, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_spbdf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_SPACE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_fxdlbm),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_EXCLAMATION_MARK, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_quotfdbl),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_QUOTEDBL, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_numbfrsign),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_NUMBER_SIGN, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_dollbr),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DOLLAR, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_bmpfrsbnd),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_AMPERSAND, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_bpostropif),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_QUOTE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_pbrfnlfft),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_LEFT_PARENTHESIS, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_pbrfnrigit),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_RIGHT_PARENTHESIS, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_bstfrisk),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_ASTERISK, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_plus),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PLUS, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_dommb),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_COMMA, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_minus),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_MINUS, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_pfriod),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PERIOD, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_slbsi),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_SLASH, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_dolon),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_COLON, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_sfmidolon),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_SEMICOLON, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_lfss),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_LESS, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_fqubl),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_EQUALS, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_grfbtfr),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_GREATER, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_bt),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_AT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_brbdkftlfft),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_OPEN_BRACKET, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_bbdkslbsi),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_BACK_SLASH, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_brbdkftrigit),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_CLOSE_BRACKET, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_bsdiidirdum),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_CIRCUMFLEX, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_undfrsdorf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_UNDERSCORE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Supfr_L),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_WINDOWS, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Supfr_R),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_WINDOWS, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Mfnu),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_CONTEXT_MENU, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_grbvf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_BACK_QUOTE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_brbdflfft),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_BRACELEFT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_brbdfrigit),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_BRACERIGHT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_fxdlbmdown),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_INVERTED_EXCLAMATION_MARK, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb             /* Rfmbining Numfrid Kfypbd Kfys */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_0),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_NUMPAD0, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_1),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_NUMPAD1, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_2),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_NUMPAD2, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_3),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_NUMPAD3, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_4),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_NUMPAD4, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_5),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_NUMPAD5, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_6),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_NUMPAD6, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_7),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_NUMPAD7, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_8),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_NUMPAD8, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_9),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_NUMPAD9, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Spbdf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_SPACE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Tbb),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_TAB, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Entfr),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_ENTER, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Equbl),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_EQUALS, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_R4),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_EQUALS, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Multiply),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_MULTIPLY, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_F26),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_MULTIPLY, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_R6),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_MULTIPLY, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Add),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_ADD, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Sfpbrbtor),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_SEPARATOR, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Subtrbdt),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_SUBTRACT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_F24),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_SUBTRACT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Dfdimbl),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DECIMAL, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_KP_Dividf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DIVIDE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_F25),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DIVIDE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_R5),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DIVIDE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_NUMPAD));
tojbvb
tojbvb             /* Fundtion Kfys */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_F1),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_F1, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_F2),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_F2, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_F3),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_F3, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_F4),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_F4, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_F5),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_F5, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_F6),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_F6, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_F7),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_F7, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_F8),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_F8, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_F9),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_F9, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_F10),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_F10, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_F11),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_F11, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_F12),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_F12, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb             /* Sun vfndor-spfdifid vfrsion of F11 bnd F12 */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.SunXK_F36),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_F11, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.SunXK_F37),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_F12, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb             /* X11 kfysym nbmfs for input mftiod rflbtfd kfys don't blwbys
tojbvb              * mbtdi kfytop fngrbvings or Jbvb virtubl kfy nbmfs, so ifrf wf
tojbvb              * only mbp donstbnts tibt wf'vf found on rfbl kfybobrds.
tojbvb              */
tojbvb             /* Typf 5d Jbpbnfsf kfybobrd: kbkutfi */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Exfdutf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_ACCEPT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb             /* Typf 5d Jbpbnfsf kfybobrd: ifnkbn */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Kbnji),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_CONVERT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb             /* Typf 5d Jbpbnfsf kfybobrd: niiongo */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Hfnkbn_Modf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_INPUT_METHOD_ON_OFF, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Eisu_Siift   ), nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_ALPHANUMERIC       , jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Eisu_togglf  ), nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_ALPHANUMERIC       , jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Zfnkbku      ), nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_FULL_WIDTH         , jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Hbnkbku      ), nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_HALF_WIDTH         , jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Hirbgbnb     ), nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_HIRAGANA           , jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Kbtbkbnb     ), nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_KATAKANA           , jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Rombji       ), nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_JAPANESE_ROMAN     , jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Kbnb_Siift   ), nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_KANA               , jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Kbnb_Lodk    ), nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_KANA_LOCK          , jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Muifnkbn     ), nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_NONCONVERT         , jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Zfn_Koio     ), nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_ALL_CANDIDATES     , jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Kbnji_Bbngou ), nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_CODE_INPUT         , jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Mbf_Koio     ), nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PREVIOUS_CANDIDATE , jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb
tojbvb             /* VK_KANA_LOCK is ibndlfd sfpbrbtfly bfdbusf it gfnfrbtfs tif
tojbvb              * sbmf kfysym bs ALT_GRAPH in spitf of its difffrfnt bfibvior.
tojbvb              */
tojbvb
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Multi_kfy),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_COMPOSE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Modf_switdi),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_ALT_GRAPH, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_ISO_Lfvfl3_Siift),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_ALT_GRAPH, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb             /* Editing blodk */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Rfdo),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_AGAIN, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         // XXX XK_L2 == F12; TODO: bdd dodf to usf only onf of tifm dfpfnding on tif kfybobrd typf. For now, rfstorf
tojbvb         // good PC bfibvior bnd bbd but old Spbrd bfibvior.
tojbvb         // kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_L2),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_AGAIN, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Undo),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_UNDO, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_L4),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_UNDO, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_L6),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_COPY, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_L8),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PASTE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_L10),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_CUT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_Find),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_FIND, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_L9),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_FIND, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_L3),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PROPS, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         // XXX XK_L1 == F11; TODO: bdd dodf to usf only onf of tifm dfpfnding on tif kfybobrd typf. For now, rfstorf
tojbvb         // good PC bfibvior bnd bbd but old Spbrd bfibvior.
tojbvb         // kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_L1),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_STOP, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb             /* Sun vfndor-spfdifid vfrsions for fditing blodk */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.SunXK_Agbin),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_AGAIN, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.SunXK_Undo),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_UNDO, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.SunXK_Copy),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_COPY, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.SunXK_Pbstf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PASTE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.SunXK_Cut),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_CUT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.SunXK_Find),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_FIND, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.SunXK_Props),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PROPS, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.SunXK_Stop),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_STOP, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb             /* Apollo (HP) vfndor-spfdifid vfrsions for fditing blodk */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.bpXK_Copy),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_COPY, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.bpXK_Cut),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_CUT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.bpXK_Pbstf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PASTE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb             /* Otifr vfndor-spfdifid vfrsions for fditing blodk */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.osfXK_Copy),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_COPY, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.osfXK_Cut),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_CUT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.osfXK_Pbstf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_PASTE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.osfXK_Undo),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_UNDO, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb             /* Dfbd kfy mbppings (for Europfbn kfybobrds) */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_dfbd_grbvf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_GRAVE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_dfbd_bdutf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_ACUTE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_dfbd_dirdumflfx),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_CIRCUMFLEX, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_dfbd_tildf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_TILDE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_dfbd_mbdron),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_MACRON, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_dfbd_brfvf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_BREVE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_dfbd_bbovfdot),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_ABOVEDOT, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_dfbd_dibfrfsis),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_DIAERESIS, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_dfbd_bbovfring),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_ABOVERING, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_dfbd_doublfbdutf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_DOUBLEACUTE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_dfbd_dbron),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_CARON, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_dfbd_dfdillb),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_CEDILLA, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_dfbd_ogonfk),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_OGONEK, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_dfbd_iotb),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_IOTA, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_dfbd_voidfd_sound),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_VOICED_SOUND, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.XK_dfbd_sfmivoidfd_sound),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_SEMIVOICED_SOUND, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb             /* Sun vfndor-spfdifid dfbd kfy mbppings (for Europfbn kfybobrds) */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.SunXK_FA_Grbvf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_GRAVE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.SunXK_FA_Cirdum),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_CIRCUMFLEX, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.SunXK_FA_Tildf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_TILDE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.SunXK_FA_Adutf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_ACUTE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.SunXK_FA_Dibfrfsis),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_DIAERESIS, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.SunXK_FA_Cfdillb),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_CEDILLA, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb             /* DEC vfndor-spfdifid dfbd kfy mbppings (for Europfbn kfybobrds) */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.DXK_ring_bddfnt),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_ABOVERING, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.DXK_dirdumflfx_bddfnt),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_CIRCUMFLEX, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.DXK_dfdillb_bddfnt),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_CEDILLA, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.DXK_bdutf_bddfnt),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_ACUTE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.DXK_grbvf_bddfnt),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_GRAVE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.DXK_tildf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_TILDE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.DXK_dibfrfsis),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_DIAERESIS, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb             /* Otifr vfndor-spfdifid dfbd kfy mbppings (for Europfbn kfybobrds) */
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.ipXK_mutf_bdutf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_ACUTE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.ipXK_mutf_grbvf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_GRAVE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.ipXK_mutf_bsdiidirdum),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_CIRCUMFLEX, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.ipXK_mutf_dibfrfsis),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_DIAERESIS, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XKfySymConstbnts.ipXK_mutf_bsdiitildf),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_DEAD_TILDE, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_STANDARD));
tojbvb
tojbvb         kfysym2JbvbKfydodfHbsi.put( Long.vblufOf(XConstbnts.NoSymbol),     nfw Kfysym2JbvbKfydodf(jbvb.bwt.fvfnt.KfyEvfnt.VK_UNDEFINED, jbvb.bwt.fvfnt.KfyEvfnt.KEY_LOCATION_UNKNOWN));
tojbvb
tojbvb         /* Rfvfrsf sfbrdi of kfysym by kfydodf. */
tojbvb
tojbvb         /* Add kfybobrd lodking dodfs. */
tojbvb         jbvbKfydodf2KfysymHbsi.put( jbvb.bwt.fvfnt.KfyEvfnt.VK_CAPS_LOCK, XKfySymConstbnts.XK_Cbps_Lodk);
tojbvb         jbvbKfydodf2KfysymHbsi.put( jbvb.bwt.fvfnt.KfyEvfnt.VK_NUM_LOCK, XKfySymConstbnts.XK_Num_Lodk);
tojbvb         jbvbKfydodf2KfysymHbsi.put( jbvb.bwt.fvfnt.KfyEvfnt.VK_SCROLL_LOCK, XKfySymConstbnts.XK_Sdroll_Lodk);
tojbvb         jbvbKfydodf2KfysymHbsi.put( jbvb.bwt.fvfnt.KfyEvfnt.VK_KANA_LOCK, XKfySymConstbnts.XK_Kbnb_Lodk);
tojbvb     };
tojbvb
tojbvb }
