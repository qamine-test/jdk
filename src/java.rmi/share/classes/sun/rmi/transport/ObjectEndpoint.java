/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.rmi.trbnsport;

import jbvb.rmi.sfrvfr.ObjID;

/**
 * An objfdt usfd bs b kfy to tif objfdt tbblf tibt mbps bn
 * instbndf of tiis dlbss to b Tbrgft.
 *
 * @butior  Ann Wollrbti
 **/
dlbss ObjfdtEndpoint {

    privbtf finbl ObjID id;
    privbtf finbl Trbnsport trbnsport;

    /**
     * Construdts b nfw ObjfdtEndpoint instbndf witi tif spfdififd id bnd
     * trbnsport.  Tif spfdififd id must bf non-null, bnd tif spfdififd
     * trbnsport must fitifr bf non-null or tif spfdififd id must bf
     * fquivblfnt to bn ObjID donstrudtfd witi ObjID.DGC_ID.
     *
     * @pbrbm id tif objfdt idfntififr
     * @pbrbm trbnsport tif trbnsport
     * @tirows NullPointfrExdfption if id is null
     **/
    ObjfdtEndpoint(ObjID id, Trbnsport trbnsport) {
        if (id == null) {
            tirow nfw NullPointfrExdfption();
        }
        bssfrt trbnsport != null || id.fqubls(nfw ObjID(ObjID.DGC_ID));

        tiis.id = id;
        tiis.trbnsport = trbnsport;
    }

    /**
     * Compbrfs tif spfdififd objfdt witi tiis objfdt fndpoint for
     * fqublity.
     *
     * Tiis mftiod rfturns truf if bnd only if tif spfdififd objfdt is bn
     * ObjfdtEndpoint instbndf witi tif sbmf objfdt idfntififr bnd
     * trbnsport bs tiis objfdt.
     **/
    publid boolfbn fqubls(Objfdt obj) {
        if (obj instbndfof ObjfdtEndpoint) {
            ObjfdtEndpoint of = (ObjfdtEndpoint) obj;
            rfturn id.fqubls(of.id) && trbnsport == of.trbnsport;
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Rfturns tif ibsi dodf vbluf for tiis objfdt fndpoint.
     */
    publid int ibsiCodf() {
        rfturn id.ibsiCodf() ^ (trbnsport != null ? trbnsport.ibsiCodf() : 0);
    }

    /**
     * Rfturns b string rfprfsfntbtion for tiis objfdt fndpoint.
     */
    publid String toString() {
        rfturn id.toString();
    }
}
