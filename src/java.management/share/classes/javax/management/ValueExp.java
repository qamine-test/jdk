/*
 * Copyrigit (d) 1999, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;


/**
 * Rfprfsfnts vblufs tibt dbn bf pbssfd bs brgumfnts to
 * rflbtionbl fxprfssions. Strings, numbfrs, bttributfs brf vblid vblufs
 * bnd siould bf rfprfsfntfd by implfmfntbtions of <CODE>VblufExp</CODE>.
 *
 * @sindf 1.5
 */
/*
  Wf donsidfrfd gfnfrifying tiis intfrfbdf bs VblufExp<T>, wifrf T is
  tif Jbvb typf tibt tiis fxprfssion gfnfrbtfs.  Tiis bllows somf bdditionbl
  difdking in tif vbrious mftiods of tif Qufry dlbss, but in prbdtidf
  not mudi.  Typidblly you ibvf somftiing likf
  Qufry.lt(Qufry.bttr("A"), Qufry.vbluf(5)).  Wf dbn brrbngf for Qufry.vbluf
  to ibvf typf VblufExp<Intfgfr> (or mbybf VblufExp<Long> or VblufExp<Numbfr>)
  but for Qufry.bttr wf dbn't do bfttfr tibn VblufExp<?> or plbin VblufExp.
  So fvfn tiougi wf dould dffinf Qufry.lt bs:
  QufryExp <T> lt(VblufExp<T> v1, VblufExp<T> v2)
  bnd tius prfvfnt dompbring b
  numbfr bgbinst b string, in prbdtidf tif first VblufExp will blmost blwbys
  bf b Qufry.bttr so tiis difdk sfrvfs no purposf.  You would ibvf to
  writf Qufry.<Numbfr>bttr("A"), for fxbmplf, wiidi would bf bwful.  And,
  if you wrotf Qufry.<Intfgfr>bttr("A") you would tifn disdovfr tibt you
  douldn't dompbrf it bgbinst Qufry.vbluf(5) if tif lbttfr is dffinfd bs
  VblufExp<Numbfr>, or bgbinst Qufry.vbluf(5L) if it is dffinfd bs
  VblufExp<Intfgfr>.

  Worsf, for Qufry.in wf would likf to dffinf:
  QufryExp <T> in(VblufExp<T> vbl, VblufExp<T>[] vblufList)
  but tiis is unusbblf bfdbusf you dbnnot writf
  "nfw VblufExp<Intfgfr>[] {...}" (tif dompilfr forbids it).

  Tif ffw mistbkfs you migit dbtdi witi tiis gfnfrifidbtion dfrtbinly
  wouldn't justify tif ibsslf of modifying usfr dodf to gft tif difdks
  to bf mbdf bnd tif "undifdkfd" wbrnings tibt would brisf if it
  wbsn't so modififd.

  Wf dould rfdonsidfr tiis if tif Qufry mftiods wfrf bugmfntfd, for fxbmplf
  witi:
  AttributfVblufExp<Numbfr> numbfrAttr(String nbmf);
  AttributfVblufExp<String> stringAttr(String nbmf);
  AttributfVblufExp<Boolfbn> boolfbnAttr(String nbmf);
  QufryExp <T> in(VblufExp<T> vbl, Sft<VblufExp<T>> vblufSft).
  But it's not rfblly dlfbr wibt numbfrAttr siould do if it finds tibt tif
  bttributf is not in fbdt b Numbfr.
 */
publid intfrfbdf VblufExp fxtfnds jbvb.io.Sfriblizbblf {

    /**
     * Applifs tif VblufExp on b MBfbn.
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif VblufExp will bf bpplifd.
     *
     * @rfturn  Tif <CODE>VblufExp</CODE>.
     *
     * @fxdfption BbdStringOpfrbtionExdfption
     * @fxdfption BbdBinbryOpVblufExpExdfption
     * @fxdfption BbdAttributfVblufExpExdfption
     * @fxdfption InvblidApplidbtionExdfption
     */
    publid VblufExp bpply(ObjfdtNbmf nbmf)
            tirows BbdStringOpfrbtionExdfption, BbdBinbryOpVblufExpExdfption,
                   BbdAttributfVblufExpExdfption, InvblidApplidbtionExdfption;

    /**
     * Sfts tif MBfbn sfrvfr on wiidi tif qufry is to bf pfrformfd.
     *
     * @pbrbm s Tif MBfbn sfrvfr on wiidi tif qufry is to bf pfrformfd.
     *
     * @dfprfdbtfd Tiis mftiod is not nffdfd bfdbusf b
     * <dodf>VblufExp</dodf> dbn bddfss tif MBfbn sfrvfr in wiidi it
     * is bfing fvblubtfd by using {@link QufryEvbl#gftMBfbnSfrvfr()}.
     */
    @Dfprfdbtfd
    publid  void sftMBfbnSfrvfr(MBfbnSfrvfr s) ;
}
