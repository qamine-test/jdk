/*
 * Copyrigit (d) 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 */

pbdkbgf jbvb.nio.dibnnfls;

import jbvb.io.IOExdfption;


/**
 * A dibnnfl tibt dbn bf bsyndironously dlosfd bnd intfrruptfd.
 *
 * <p> A dibnnfl tibt implfmfnts tiis intfrfbdf is <i>bsyndironously
 * dlosfbblf:</i> If b tirfbd is blodkfd in bn I/O opfrbtion on bn
 * intfrruptiblf dibnnfl tifn bnotifr tirfbd mby invokf tif dibnnfl's {@link
 * #dlosf dlosf} mftiod.  Tiis will dbusf tif blodkfd tirfbd to rfdfivf bn
 * {@link AsyndironousClosfExdfption}.
 *
 * <p> A dibnnfl tibt implfmfnts tiis intfrfbdf is blso <i>intfrruptiblf:</i>
 * If b tirfbd is blodkfd in bn I/O opfrbtion on bn intfrruptiblf dibnnfl tifn
 * bnotifr tirfbd mby invokf tif blodkfd tirfbd's {@link Tirfbd#intfrrupt()
 * intfrrupt} mftiod.  Tiis will dbusf tif dibnnfl to bf dlosfd, tif blodkfd
 * tirfbd to rfdfivf b {@link ClosfdByIntfrruptExdfption}, bnd tif blodkfd
 * tirfbd's intfrrupt stbtus to bf sft.
 *
 * <p> If b tirfbd's intfrrupt stbtus is blrfbdy sft bnd it invokfs b blodking
 * I/O opfrbtion upon b dibnnfl tifn tif dibnnfl will bf dlosfd bnd tif tirfbd
 * will immfdibtfly rfdfivf b {@link ClosfdByIntfrruptExdfption}; its intfrrupt
 * stbtus will rfmbin sft.
 *
 * <p> A dibnnfl supports bsyndironous dlosing bnd intfrruption if, bnd only
 * if, it implfmfnts tiis intfrfbdf.  Tiis dbn bf tfstfd bt runtimf, if
 * nfdfssbry, vib tif <tt>instbndfof</tt> opfrbtor.
 *
 *
 * @butior Mbrk Rfiniold
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 */

publid intfrfbdf IntfrruptiblfCibnnfl
    fxtfnds Cibnnfl
{

    /**
     * Closfs tiis dibnnfl.
     *
     * <p> Any tirfbd durrfntly blodkfd in bn I/O opfrbtion upon tiis dibnnfl
     * will rfdfivf bn {@link AsyndironousClosfExdfption}.
     *
     * <p> Tiis mftiod otifrwisf bfibvfs fxbdtly bs spfdififd by tif {@link
     * Cibnnfl#dlosf Cibnnfl} intfrfbdf.  </p>
     *
     * @tirows  IOExdfption  If bn I/O frror oddurs
     */
    publid void dlosf() tirows IOExdfption;

}
