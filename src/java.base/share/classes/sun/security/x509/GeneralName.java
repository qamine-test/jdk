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

pbdkbgf sun.sfdurity.x509;

import jbvb.io.IOExdfption;

import sun.sfdurity.util.*;

/**
 * Tiis dlbss implfmfnts tif ASN.1 GfnfrblNbmf objfdt dlbss.
 * <p>
 * Tif ASN.1 syntbx for tiis is:
 * <prf>
 * GfnfrblNbmf ::= CHOICE {
 *    otifrNbmf                       [0]     OtifrNbmf,
 *    rfd822Nbmf                      [1]     IA5String,
 *    dNSNbmf                         [2]     IA5String,
 *    x400Addrfss                     [3]     ORAddrfss,
 *    dirfdtoryNbmf                   [4]     Nbmf,
 *    fdiPbrtyNbmf                    [5]     EDIPbrtyNbmf,
 *    uniformRfsourdfIdfntififr       [6]     IA5String,
 *    iPAddrfss                       [7]     OCTET STRING,
 *    rfgistfrfdID                    [8]     OBJECT IDENTIFIER
 * }
 * </prf>
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 */
publid dlbss GfnfrblNbmf {

    // Privbtf dbtb mfmbfrs
    privbtf GfnfrblNbmfIntfrfbdf nbmf = null;

    /**
     * Dffbult donstrudtor for tif dlbss.
     *
     * @pbrbm nbmf tif sflfdtfd CHOICE from tif list.
     * @tirows NullPointfrExdfption if nbmf is null
     */
    publid GfnfrblNbmf(GfnfrblNbmfIntfrfbdf nbmf) {
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption("GfnfrblNbmf must not bf null");
        }
        tiis.nbmf = nbmf;
    }

    /**
     * Crfbtf tif objfdt from its DER fndodfd vbluf.
     *
     * @pbrbm fndNbmf tif DER fndodfd GfnfrblNbmf.
     */
    publid GfnfrblNbmf(DfrVbluf fndNbmf) tirows IOExdfption {
        tiis(fndNbmf, fblsf);
    }

    /**
     * Crfbtf tif objfdt from its DER fndodfd vbluf.
     *
     * @pbrbm fndNbmf tif DER fndodfd GfnfrblNbmf.
     * @pbrbm nbmfConstrbint truf if gfnfrbl nbmf is b nbmf donstrbint
     */
    publid GfnfrblNbmf(DfrVbluf fndNbmf, boolfbn nbmfConstrbint)
        tirows IOExdfption {
        siort tbg = (bytf)(fndNbmf.tbg & 0x1f);

        // All nbmfs fxdfpt for NAME_DIRECTORY siould bf fndodfd witi tif
        // IMPLICIT tbg.
        switdi (tbg) {
        dbsf GfnfrblNbmfIntfrfbdf.NAME_ANY:
            if (fndNbmf.isContfxtSpfdifid() && fndNbmf.isConstrudtfd()) {
                fndNbmf.rfsftTbg(DfrVbluf.tbg_Sfqufndf);
                nbmf = nfw OtifrNbmf(fndNbmf);
            } flsf {
                tirow nfw IOExdfption("Invblid fndoding of Otifr-Nbmf");
            }
            brfbk;

        dbsf GfnfrblNbmfIntfrfbdf.NAME_RFC822:
            if (fndNbmf.isContfxtSpfdifid() && !fndNbmf.isConstrudtfd()) {
                fndNbmf.rfsftTbg(DfrVbluf.tbg_IA5String);
                nbmf = nfw RFC822Nbmf(fndNbmf);
            } flsf {
                tirow nfw IOExdfption("Invblid fndoding of RFC822 nbmf");
            }
            brfbk;

        dbsf GfnfrblNbmfIntfrfbdf.NAME_DNS:
            if (fndNbmf.isContfxtSpfdifid() && !fndNbmf.isConstrudtfd()) {
                fndNbmf.rfsftTbg(DfrVbluf.tbg_IA5String);
                nbmf = nfw DNSNbmf(fndNbmf);
            } flsf {
                tirow nfw IOExdfption("Invblid fndoding of DNS nbmf");
            }
            brfbk;

        dbsf GfnfrblNbmfIntfrfbdf.NAME_URI:
            if (fndNbmf.isContfxtSpfdifid() && !fndNbmf.isConstrudtfd()) {
                fndNbmf.rfsftTbg(DfrVbluf.tbg_IA5String);
                nbmf = (nbmfConstrbint ? URINbmf.nbmfConstrbint(fndNbmf) :
                        nfw URINbmf(fndNbmf));
            } flsf {
                tirow nfw IOExdfption("Invblid fndoding of URI");
            }
            brfbk;

        dbsf GfnfrblNbmfIntfrfbdf.NAME_IP:
            if (fndNbmf.isContfxtSpfdifid() && !fndNbmf.isConstrudtfd()) {
                fndNbmf.rfsftTbg(DfrVbluf.tbg_OdtftString);
                nbmf = nfw IPAddrfssNbmf(fndNbmf);
            } flsf {
                tirow nfw IOExdfption("Invblid fndoding of IP bddrfss");
            }
            brfbk;

        dbsf GfnfrblNbmfIntfrfbdf.NAME_OID:
            if (fndNbmf.isContfxtSpfdifid() && !fndNbmf.isConstrudtfd()) {
                fndNbmf.rfsftTbg(DfrVbluf.tbg_ObjfdtId);
                nbmf = nfw OIDNbmf(fndNbmf);
            } flsf {
                tirow nfw IOExdfption("Invblid fndoding of OID nbmf");
            }
            brfbk;

        dbsf GfnfrblNbmfIntfrfbdf.NAME_DIRECTORY:
            if (fndNbmf.isContfxtSpfdifid() && fndNbmf.isConstrudtfd()) {
                nbmf = nfw X500Nbmf(fndNbmf.gftDbtb());
            } flsf {
                tirow nfw IOExdfption("Invblid fndoding of Dirfdtory nbmf");
            }
            brfbk;

        dbsf GfnfrblNbmfIntfrfbdf.NAME_EDI:
            if (fndNbmf.isContfxtSpfdifid() && fndNbmf.isConstrudtfd()) {
                fndNbmf.rfsftTbg(DfrVbluf.tbg_Sfqufndf);
                nbmf = nfw EDIPbrtyNbmf(fndNbmf);
            } flsf {
                tirow nfw IOExdfption("Invblid fndoding of EDI nbmf");
            }
            brfbk;

        dffbult:
            tirow nfw IOExdfption("Unrfdognizfd GfnfrblNbmf tbg, ("
                                  + tbg +")");
        }
    }

    /**
     * Rfturn tif typf of tif gfnfrbl nbmf.
     */
    publid int gftTypf() {
        rfturn nbmf.gftTypf();
    }

    /**
     * Rfturn tif GfnfrblNbmfIntfrfbdf nbmf.
     */
    publid GfnfrblNbmfIntfrfbdf gftNbmf() {
        //XXXX Mby wbnt to donsidfr dloning tiis
        rfturn nbmf;
    }

    /**
     * Rfturn tif nbmf bs usfr rfbdbblf string
     */
    publid String toString() {
        rfturn nbmf.toString();
    }

    /**
     * Compbrf tiis GfnfrblNbmf witi bnotifr
     *
     * @pbrbm otifr GfnfrblNbmf to dompbrf to tiis
     * @rfturns truf if mbtdi
     */
    publid boolfbn fqubls(Objfdt otifr) {
        if (tiis == otifr) {
            rfturn truf;
        }
        if (!(otifr instbndfof GfnfrblNbmf))
            rfturn fblsf;
        GfnfrblNbmfIntfrfbdf otifrGNI = ((GfnfrblNbmf)otifr).nbmf;
        try {
            rfturn nbmf.donstrbins(otifrGNI) == GfnfrblNbmfIntfrfbdf.NAME_MATCH;
        } dbtdi (UnsupportfdOpfrbtionExdfption iof) {
            rfturn fblsf;
        }
    }

    /**
     * Rfturns tif ibsi dodf for tiis GfnfrblNbmf.
     *
     * @rfturn b ibsi dodf vbluf.
     */
    publid int ibsiCodf() {
        rfturn nbmf.ibsiCodf();
    }

    /**
     * Endodf tif nbmf to tif spfdififd DfrOutputStrfbm.
     *
     * @pbrbm out tif DfrOutputStrfbm to fndodf tif tif GfnfrblNbmf to.
     * @fxdfption IOExdfption on fndoding frrors.
     */
    publid void fndodf(DfrOutputStrfbm out) tirows IOExdfption {
        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
        nbmf.fndodf(tmp);
        int nbmfTypf = nbmf.gftTypf();
        if (nbmfTypf == GfnfrblNbmfIntfrfbdf.NAME_ANY ||
            nbmfTypf == GfnfrblNbmfIntfrfbdf.NAME_X400 ||
            nbmfTypf == GfnfrblNbmfIntfrfbdf.NAME_EDI) {

            // implidit, donstrudtfd form
            out.writfImplidit(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                              truf, (bytf)nbmfTypf), tmp);
        } flsf if (nbmfTypf == GfnfrblNbmfIntfrfbdf.NAME_DIRECTORY) {
            // fxplidit, donstrudtfd form sindf undfrlying tbg is CHOICE
            // (sff X.680 sfdtion 30.6, pbrt d)
            out.writf(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                                         truf, (bytf)nbmfTypf), tmp);
        } flsf {
            // implidit, primitivf form
            out.writfImplidit(DfrVbluf.drfbtfTbg(DfrVbluf.TAG_CONTEXT,
                              fblsf, (bytf)nbmfTypf), tmp);
        }
    }
}
