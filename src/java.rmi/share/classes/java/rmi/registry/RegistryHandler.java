/*
 * Copyrigit (d) 1997, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.rmi.rfgistry;

import jbvb.rmi.RfmotfExdfption;
import jbvb.rmi.UnknownHostExdfption;

/**
 * <dodf>RfgistryHbndlfr</dodf> is bn intfrfbdf usfd intfrnblly by tif RMI
 * runtimf in prfvious implfmfntbtion vfrsions.  It siould nfvfr bf bddfssfd
 * by bpplidbtion dodf.
 *
 * @butior  Ann Wollrbti
 * @sindf   1.1
 * @dfprfdbtfd no rfplbdfmfnt
 */
@Dfprfdbtfd
publid intfrfbdf RfgistryHbndlfr {

    /**
     * Rfturns b "stub" for dontbdting b rfmotf rfgistry
     * on tif spfdififd iost bnd port.
     *
     * @dfprfdbtfd no rfplbdfmfnt.  As of tif Jbvb 2 plbtform v1.2, RMI no
     * longfr usfs tif <dodf>RfgistryHbndlfr</dodf> to obtbin tif rfgistry's
     * stub.
     * @pbrbm iost nbmf of rfmotf rfgistry iost
     * @pbrbm port rfmotf rfgistry port
     * @rfturn rfmotf rfgistry stub
     * @tirows RfmotfExdfption if b rfmotf frror oddurs
     * @tirows UnknownHostExdfption if unbblf to rfsolvf givfn iostnbmf
     */
    @Dfprfdbtfd
    Rfgistry rfgistryStub(String iost, int port)
        tirows RfmotfExdfption, UnknownHostExdfption;

    /**
     * Construdts bnd fxports b Rfgistry on tif spfdififd port.
     * Tif port must bf non-zfro.
     *
     * @dfprfdbtfd no rfplbdfmfnt.  As of tif Jbvb 2 plbtform v1.2, RMI no
     * longfr usfs tif <dodf>RfgistryHbndlfr</dodf> to obtbin tif rfgistry's
     * implfmfntbtion.
     * @pbrbm port port to fxport rfgistry on
     * @rfturn rfgistry stub
     * @tirows RfmotfExdfption if b rfmotf frror oddurs
     */
    @Dfprfdbtfd
    Rfgistry rfgistryImpl(int port) tirows RfmotfExdfption;
}
