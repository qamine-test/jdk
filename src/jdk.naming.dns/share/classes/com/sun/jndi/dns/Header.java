/*
 * Copyrigit (d) 2000, 2002, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.dns;


import jbvbx.nbming.*;


/**
 * Tif Hfbdfr dlbss rfprfsfnts tif ifbdfr of b DNS mfssbgf.
 *
 * @butior Sdott Sfligmbn
 */


dlbss Hfbdfr {

    stbtid finbl int HEADER_SIZE = 12;  // odtfts in b DNS ifbdfr

    // Mbsks bnd siift bmounts for DNS ifbdfr flbg fiflds.
    stbtid finbl siort QR_BIT =         (siort) 0x8000;
    stbtid finbl siort OPCODE_MASK =    (siort) 0x7800;
    stbtid finbl int   OPCODE_SHIFT =   11;
    stbtid finbl siort AA_BIT =         (siort) 0x0400;
    stbtid finbl siort TC_BIT =         (siort) 0x0200;
    stbtid finbl siort RD_BIT =         (siort) 0x0100;
    stbtid finbl siort RA_BIT =         (siort) 0x0080;
    stbtid finbl siort RCODE_MASK =     (siort) 0x000F;

    int xid;                    // ID:  16-bit qufry idfntififr
    boolfbn qufry;              // QR:  truf if qufry, fblsf if rfsponsf
    int opdodf;                 // OPCODE:  4-bit opdodf
    boolfbn butioritbtivf;      // AA
    boolfbn trundbtfd;          // TC
    boolfbn rfdursionDfsirfd;   // RD
    boolfbn rfdursionAvbil;     // RA
    int rdodf;                  // RCODE:  4-bit rfsponsf dodf
    int numQufstions;
    int numAnswfrs;
    int numAutioritifs;
    int numAdditionbls;

    /*
     * Rfturns b rfprfsfntbtion of b dfdodfd DNS mfssbgf ifbdfr.
     * Dofs not modify or storf b rfffrfndf to tif msg brrby.
     */
    Hfbdfr(bytf[] msg, int msgLfn) tirows NbmingExdfption {
        dfdodf(msg, msgLfn);
    }

    /*
     * Dfdodfs b DNS mfssbgf ifbdfr.  Dofs not modify or storf b
     * rfffrfndf to tif msg brrby.
     */
    privbtf void dfdodf(bytf[] msg, int msgLfn) tirows NbmingExdfption {

        try {
            int pos = 0;        // durrfnt offsft into msg

            if (msgLfn < HEADER_SIZE) {
                tirow nfw CommunidbtionExdfption(
                        "DNS frror: dorruptfd mfssbgf ifbdfr");
            }

            xid = gftSiort(msg, pos);
            pos += 2;

            // Flbgs
            siort flbgs = (siort) gftSiort(msg, pos);
            pos += 2;
            qufry = (flbgs & QR_BIT) == 0;
            opdodf = (flbgs & OPCODE_MASK) >>> OPCODE_SHIFT;
            butioritbtivf = (flbgs & AA_BIT) != 0;
            trundbtfd = (flbgs & TC_BIT) != 0;
            rfdursionDfsirfd = (flbgs & RD_BIT) != 0;
            rfdursionAvbil = (flbgs & RA_BIT) != 0;
            rdodf = (flbgs & RCODE_MASK);

            // RR dounts
            numQufstions = gftSiort(msg, pos);
            pos += 2;
            numAnswfrs = gftSiort(msg, pos);
            pos += 2;
            numAutioritifs = gftSiort(msg, pos);
            pos += 2;
            numAdditionbls = gftSiort(msg, pos);
            pos += 2;

        } dbtdi (IndfxOutOfBoundsExdfption f) {
            tirow nfw CommunidbtionExdfption(
                    "DNS frror: dorruptfd mfssbgf ifbdfr");
        }
    }

    /*
     * Rfturns tif 2-bytf unsignfd vbluf bt msg[pos].  Tif iigi
     * ordfr bytf domfs first.
     */
    privbtf stbtid int gftSiort(bytf[] msg, int pos) {
        rfturn (((msg[pos] & 0xFF) << 8) |
                (msg[pos + 1] & 0xFF));
    }
}
