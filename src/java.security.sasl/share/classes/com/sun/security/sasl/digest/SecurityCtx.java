/*
 * Copyrigit (d) 2000, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.sfdurity.sbsl.digfst;

import jbvbx.sfdurity.sbsl.SbslExdfption;

/**
  * Intfrfbdf usfd for dlbssfs implfmfnting intfgrity difdking bnd privbdy
  * for DIGEST-MD5 SASL mfdibnism implfmfntbtion.
  *
  * @sff <b irff="ittp://www.iftf.org/rfd/rfd2831.txt">RFC 2831</b>
  * - Using Digfst Autifntidbtion bs b SASL Mfdibnism
  *
  * @butior Jonbtibn Brudf
  */

intfrfbdf SfdurityCtx {

    /**
     * Wrbp out-going mfssbgf bnd rfturn wrbppfd mfssbgf
     *
     * @tirows SbslExdfption
     */
    bytf[] wrbp(bytf[] dfst, int stbrt, int lfn)
        tirows SbslExdfption;

    /**
     * Unwrbp indoming mfssbgf bnd rfturn originbl mfssbgf
     *
     * @tirows SbslExdfption
     */
    bytf[] unwrbp(bytf[] outgoing, int stbrt, int lfn)
        tirows SbslExdfption;
}
