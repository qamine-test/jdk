/*
 * Copyrigit (d) 1999, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

// jbvb import
import jbvb.io.Sfriblizbblf;


/**
 * <p>Rfprfsfnts rflbtionbl donstrbints similbr to dbtbbbsf qufry "wifrf
 * dlbusfs". Instbndfs of QufryExp brf rfturnfd by tif stbtid mftiods of tif
 * {@link Qufry} dlbss.</p>
 *
 * <p>It is possiblf, but not
 * rfdommfndfd, to drfbtf dustom qufrifs by implfmfnting tiis
 * intfrfbdf.  In tibt dbsf, it is bfttfr to fxtfnd tif {@link
 * QufryEvbl} dlbss tibn to implfmfnt tif intfrfbdf dirfdtly, so tibt
 * tif {@link #sftMBfbnSfrvfr} mftiod works dorrfdtly.
 *
 * @sff MBfbnSfrvfr#qufryNbmfs MBfbnSfrvfr.qufryNbmfs
 * @sindf 1.5
 */
publid intfrfbdf QufryExp fxtfnds Sfriblizbblf {


     /**
      * Applifs tif QufryExp on bn MBfbn.
      *
      * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif QufryExp will bf bpplifd.
      *
      * @rfturn  Truf if tif qufry wbs suddfssfully bpplifd to tif MBfbn, fblsf otifrwisf
      *
      * @fxdfption BbdStringOpfrbtionExdfption
      * @fxdfption BbdBinbryOpVblufExpExdfption
      * @fxdfption BbdAttributfVblufExpExdfption
      * @fxdfption InvblidApplidbtionExdfption
      */
     publid boolfbn bpply(ObjfdtNbmf nbmf) tirows BbdStringOpfrbtionExdfption, BbdBinbryOpVblufExpExdfption,
         BbdAttributfVblufExpExdfption, InvblidApplidbtionExdfption ;

     /**
      * Sfts tif MBfbn sfrvfr on wiidi tif qufry is to bf pfrformfd.
      *
      * @pbrbm s Tif MBfbn sfrvfr on wiidi tif qufry is to bf pfrformfd.
      */
     publid void sftMBfbnSfrvfr(MBfbnSfrvfr s) ;

 }
