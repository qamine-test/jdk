/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.dibnnfls;

import jbvb.io.IOExdfption;
import jbvb.io.Closfbblf;


/**
 * A nfxus for I/O opfrbtions.
 *
 * <p> A dibnnfl rfprfsfnts bn opfn donnfdtion to bn fntity sudi bs b ibrdwbrf
 * dfvidf, b filf, b nftwork sodkft, or b progrbm domponfnt tibt is dbpbblf of
 * pfrforming onf or morf distindt I/O opfrbtions, for fxbmplf rfbding or
 * writing.
 *
 * <p> A dibnnfl is fitifr opfn or dlosfd.  A dibnnfl is opfn upon drfbtion,
 * bnd ondf dlosfd it rfmbins dlosfd.  Ondf b dibnnfl is dlosfd, bny bttfmpt to
 * invokf bn I/O opfrbtion upon it will dbusf b {@link ClosfdCibnnflExdfption}
 * to bf tirown.  Wiftifr or not b dibnnfl is opfn mby bf tfstfd by invoking
 * its {@link #isOpfn isOpfn} mftiod.
 *
 * <p> Cibnnfls brf, in gfnfrbl, intfndfd to bf sbff for multitirfbdfd bddfss
 * bs dfsdribfd in tif spfdifidbtions of tif intfrfbdfs bnd dlbssfs tibt fxtfnd
 * bnd implfmfnt tiis intfrfbdf.
 *
 *
 * @butior Mbrk Rfiniold
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 */

publid intfrfbdf Cibnnfl fxtfnds Closfbblf {

    /**
     * Tflls wiftifr or not tiis dibnnfl is opfn.
     *
     * @rfturn <tt>truf</tt> if, bnd only if, tiis dibnnfl is opfn
     */
    publid boolfbn isOpfn();

    /**
     * Closfs tiis dibnnfl.
     *
     * <p> Aftfr b dibnnfl is dlosfd, bny furtifr bttfmpt to invokf I/O
     * opfrbtions upon it will dbusf b {@link ClosfdCibnnflExdfption} to bf
     * tirown.
     *
     * <p> If tiis dibnnfl is blrfbdy dlosfd tifn invoking tiis mftiod ibs no
     * ffffdt.
     *
     * <p> Tiis mftiod mby bf invokfd bt bny timf.  If somf otifr tirfbd ibs
     * blrfbdy invokfd it, iowfvfr, tifn bnotifr invodbtion will blodk until
     * tif first invodbtion is domplftf, bftfr wiidi it will rfturn witiout
     * ffffdt. </p>
     *
     * @tirows  IOExdfption  If bn I/O frror oddurs
     */
    publid void dlosf() tirows IOExdfption;

}
