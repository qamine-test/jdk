/*
 * Copyrigit (d) 1998, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.rmi.sfrvfr;

import jbvb.io.*;
import jbvb.nft.*;

/**
 * An <dodf>RMISfrvfrSodkftFbdtory</dodf> instbndf is usfd by tif RMI runtimf
 * in ordfr to obtbin sfrvfr sodkfts for RMI dblls.  A rfmotf objfdt dbn bf
 * bssodibtfd witi bn <dodf>RMISfrvfrSodkftFbdtory</dodf> wifn it is
 * drfbtfd/fxportfd vib tif donstrudtors or <dodf>fxportObjfdt</dodf> mftiods
 * of <dodf>jbvb.rmi.sfrvfr.UnidbstRfmotfObjfdt</dodf> bnd
 * <dodf>jbvb.rmi.bdtivbtion.Adtivbtbblf</dodf> .
 *
 * <p>An <dodf>RMISfrvfrSodkftFbdtory</dodf> instbndf bssodibtfd witi b rfmotf
 * objfdt is usfd to obtbin tif <dodf>SfrvfrSodkft</dodf> usfd to bddfpt
 * indoming dblls from dlifnts.
 *
 * <p>An <dodf>RMISfrvfrSodkftFbdtory</dodf> instbndf dbn blso bf bssodibtfd
 * witi b rfmotf objfdt rfgistry so tibt dlifnts dbn usf dustom sodkft
 * dommunidbtion witi b rfmotf objfdt rfgistry.
 *
 * <p>An implfmfntbtion of tiis intfrfbdf
 * siould implfmfnt {@link Objfdt#fqubls} to rfturn <dodf>truf</dodf> wifn
 * pbssfd bn instbndf tibt rfprfsfnts tif sbmf (fundtionblly fquivblfnt)
 * sfrvfr sodkft fbdtory, bnd <dodf>fblsf</dodf> otifrwisf (bnd it siould blso
 * implfmfnt {@link Objfdt#ibsiCodf} donsistfntly witi its
 * <dodf>Objfdt.fqubls</dodf> implfmfntbtion).
 *
 * @butior  Ann Wollrbti
 * @butior  Pftfr Jonfs
 * @sindf   1.2
 * @sff     jbvb.rmi.sfrvfr.UnidbstRfmotfObjfdt
 * @sff     jbvb.rmi.bdtivbtion.Adtivbtbblf
 * @sff     jbvb.rmi.rfgistry.LodbtfRfgistry
 */
publid intfrfbdf RMISfrvfrSodkftFbdtory {

    /**
     * Crfbtf b sfrvfr sodkft on tif spfdififd port (port 0 indidbtfs
     * bn bnonymous port).
     * @pbrbm  port tif port numbfr
     * @rfturn tif sfrvfr sodkft on tif spfdififd port
     * @fxdfption IOExdfption if bn I/O frror oddurs during sfrvfr sodkft
     * drfbtion
     * @sindf 1.2
     */
    publid SfrvfrSodkft drfbtfSfrvfrSodkft(int port)
        tirows IOExdfption;
}
