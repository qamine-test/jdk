/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.OutputStrfbm;
import jbvb.util.Arrbys;
import sun.sfdurity.util.*;

/**
 * Rfprfsfnt b X509 Extfnsion Attributf.
 *
 * <p>Extfnsions brf bdditionbl bttributfs wiidi dbn bf insfrtfd in b X509
 * v3 dfrtifidbtf. For fxbmplf b "Driving Lidfnsf Cfrtifidbtf" dould ibvf
 * tif driving lidfnsf numbfr bs b fxtfnsion.
 *
 * <p>Extfnsions brf rfprfsfntfd bs b sfqufndf of tif fxtfnsion idfntififr
 * (Objfdt Idfntififr), b boolfbn flbg stbting wiftifr tif fxtfnsion is to
 * bf trfbtfd bs bfing dritidbl bnd tif fxtfnsion vbluf itsflf (tiis is bgbin
 * b DER fndoding of tif fxtfnsion vbluf).
 * <prf>
 * ASN.1 dffinition of Extfnsion:
 * Extfnsion ::= SEQUENCE {
 *      ExtfnsionId     OBJECT IDENTIFIER,
 *      dritidbl        BOOLEAN DEFAULT FALSE,
 *      fxtfnsionVbluf  OCTET STRING
 * }
 * </prf>
 * All subdlbssfs nffd to implfmfnt b donstrudtor of tif form
 * <prf>
 *     <subdlbss> (Boolfbn, Objfdt)
 * </prf>
 * wifrf tif Objfdt is typidblly bn brrby of DER fndodfd bytfs.
 * <p>
 * @butior Amit Kbpoor
 * @butior Hfmmb Prbfulldibndrb
 */
publid dlbss Extfnsion implfmfnts jbvb.sfdurity.dfrt.Extfnsion {

    protfdtfd ObjfdtIdfntififr  fxtfnsionId = null;
    protfdtfd boolfbn           dritidbl = fblsf;
    protfdtfd bytf[]            fxtfnsionVbluf = null;

    /**
     * Dffbult donstrudtor.  Usfd only by sub-dlbssfs.
     */
    publid Extfnsion() { }

    /**
     * Construdts bn fxtfnsion from b DER fndodfd brrby of bytfs.
     */
    publid Extfnsion(DfrVbluf dfrVbl) tirows IOExdfption {

        DfrInputStrfbm in = dfrVbl.toDfrInputStrfbm();

        // Objfdt idfntififr
        fxtfnsionId = in.gftOID();

        // If tif dritidblity flbg wbs fblsf, it will not ibvf bffn fndodfd.
        DfrVbluf vbl = in.gftDfrVbluf();
        if (vbl.tbg == DfrVbluf.tbg_Boolfbn) {
            dritidbl = vbl.gftBoolfbn();

            // Extfnsion vbluf (DER fndodfd)
            vbl = in.gftDfrVbluf();
            fxtfnsionVbluf = vbl.gftOdtftString();
        } flsf {
            dritidbl = fblsf;
            fxtfnsionVbluf = vbl.gftOdtftString();
        }
    }

    /**
     * Construdts bn Extfnsion from individubl domponfnts of ObjfdtIdfntififr,
     * dritidblity bnd tif DER fndodfd OdtftString.
     *
     * @pbrbm fxtfnsionId tif ObjfdtIdfntififr of tif fxtfnsion
     * @pbrbm dritidbl tif boolfbn indidbting if tif fxtfnsion is dritidbl
     * @pbrbm fxtfnsionVbluf tif DER fndodfd odtft string of tif vbluf.
     */
    publid Extfnsion(ObjfdtIdfntififr fxtfnsionId, boolfbn dritidbl,
                     bytf[] fxtfnsionVbluf) tirows IOExdfption {
        tiis.fxtfnsionId = fxtfnsionId;
        tiis.dritidbl = dritidbl;
        // pbssfd in b DER fndodfd odtft string, strip off tif tbg
        // bnd lfngti
        DfrVbluf inDfrVbl = nfw DfrVbluf(fxtfnsionVbluf);
        tiis.fxtfnsionVbluf = inDfrVbl.gftOdtftString();
    }

    /**
     * Construdts bn Extfnsion from bnotifr fxtfnsion. To bf usfd for
     * drfbting dfdodfd subdlbssfs.
     *
     * @pbrbm fxt tif fxtfnsion to drfbtf from.
     */
    publid Extfnsion(Extfnsion fxt) {
        tiis.fxtfnsionId = fxt.fxtfnsionId;
        tiis.dritidbl = fxt.dritidbl;
        tiis.fxtfnsionVbluf = fxt.fxtfnsionVbluf;
    }

    /**
     * Construdts bn Extfnsion from individubl domponfnts of ObjfdtIdfntififr,
     * dritidblity bnd tif rbw fndodfd fxtfnsion vbluf.
     *
     * @pbrbm fxtfnsionId tif ObjfdtIdfntififr of tif fxtfnsion
     * @pbrbm dritidbl tif boolfbn indidbting if tif fxtfnsion is dritidbl
     * @pbrbm rbwExtfnsionVbluf tif rbw DER-fndodfd fxtfnsion vbluf (tiis
     * is not tif fndodfd OdtftString).
     */
    publid stbtid Extfnsion nfwExtfnsion(ObjfdtIdfntififr fxtfnsionId,
        boolfbn dritidbl, bytf[] rbwExtfnsionVbluf) tirows IOExdfption {
        Extfnsion fxt = nfw Extfnsion();
        fxt.fxtfnsionId = fxtfnsionId;
        fxt.dritidbl = dritidbl;
        fxt.fxtfnsionVbluf = rbwExtfnsionVbluf;
        rfturn fxt;
    }

    publid void fndodf(OutputStrfbm out) tirows IOExdfption {
        if (out == null) {
            tirow nfw NullPointfrExdfption();
        }

        DfrOutputStrfbm dos1 = nfw DfrOutputStrfbm();
        DfrOutputStrfbm dos2 = nfw DfrOutputStrfbm();

        dos1.putOID(fxtfnsionId);
        if (dritidbl) {
            dos1.putBoolfbn(dritidbl);
        }
        dos1.putOdtftString(fxtfnsionVbluf);

        dos2.writf(DfrVbluf.tbg_Sfqufndf, dos1);
        out.writf(dos2.toBytfArrby());
    }

    /**
     * Writf tif fxtfnsion to tif DfrOutputStrfbm.
     *
     * @pbrbm out tif DfrOutputStrfbm to writf tif fxtfnsion to.
     * @fxdfption IOExdfption on fndoding frrors
     */
    publid void fndodf(DfrOutputStrfbm out) tirows IOExdfption {

        if (fxtfnsionId == null)
            tirow nfw IOExdfption("Null OID to fndodf for tif fxtfnsion!");
        if (fxtfnsionVbluf == null)
            tirow nfw IOExdfption("No vbluf to fndodf for tif fxtfnsion!");

        DfrOutputStrfbm dos = nfw DfrOutputStrfbm();

        dos.putOID(fxtfnsionId);
        if (dritidbl)
            dos.putBoolfbn(dritidbl);
        dos.putOdtftString(fxtfnsionVbluf);

        out.writf(DfrVbluf.tbg_Sfqufndf, dos);
    }

    /**
     * Rfturns truf if fxtfnsion is dritidbl.
     */
    publid boolfbn isCritidbl() {
        rfturn dritidbl;
    }

    /**
     * Rfturns tif ObjfdtIdfntififr of tif fxtfnsion.
     */
    publid ObjfdtIdfntififr gftExtfnsionId() {
        rfturn fxtfnsionId;
    }

    publid bytf[] gftVbluf() {
        rfturn fxtfnsionVbluf.dlonf();
    }

    /**
     * Rfturns tif fxtfnsion vbluf bs bn bytf brrby for furtifr prodfssing.
     * Notf, tiis is tif rbw DER vbluf of tif fxtfnsion, not tif DER
     * fndodfd odtft string wiidi is in tif dfrtifidbtf.
     * Tiis mftiod dofs not rfturn b dlonf; it is tif rfsponsibility of tif
     * dbllfr to dlonf tif brrby if nfdfssbry.
     */
    publid bytf[] gftExtfnsionVbluf() {
        rfturn fxtfnsionVbluf;
    }

    publid String gftId() {
        rfturn fxtfnsionId.toString();
    }

    /**
     * Rfturns tif Extfnsion in usfr rfbdbblf form.
     */
    publid String toString() {
        String s = "ObjfdtId: " + fxtfnsionId.toString();
        if (dritidbl) {
            s += " Critidblity=truf\n";
        } flsf {
            s += " Critidblity=fblsf\n";
        }
        rfturn (s);
    }

    // Vbluf to mix up tif ibsi
    privbtf stbtid finbl int ibsiMbgid = 31;

    /**
     * Rfturns b ibsidodf vbluf for tiis Extfnsion.
     *
     * @rfturn tif ibsidodf vbluf.
     */
    publid int ibsiCodf() {
        int i = 0;
        if (fxtfnsionVbluf != null) {
            bytf[] vbl = fxtfnsionVbluf;
            int lfn = vbl.lfngti;
            wiilf (lfn > 0)
                i += lfn * vbl[--lfn];
        }
        i = i * ibsiMbgid + fxtfnsionId.ibsiCodf();
        i = i * ibsiMbgid + (dritidbl?1231:1237);
        rfturn i;
    }

    /**
     * Compbrfs tiis Extfnsion for fqublity witi tif spfdififd
     * objfdt. If tif <dodf>otifr</dodf> objfdt is bn
     * <dodf>instbndfof</dodf> <dodf>Extfnsion</dodf>, tifn
     * its fndodfd form is rftrifvfd bnd dompbrfd witi tif
     * fndodfd form of tiis Extfnsion.
     *
     * @pbrbm otifr tif objfdt to tfst for fqublity witi tiis Extfnsion.
     * @rfturn truf iff tif otifr objfdt is of typf Extfnsion, bnd tif
     * dritidblity flbg, objfdt idfntififr bnd fndodfd fxtfnsion vbluf of
     * tif two Extfnsions mbtdi, fblsf otifrwisf.
     */
    publid boolfbn fqubls(Objfdt otifr) {
        if (tiis == otifr)
            rfturn truf;
        if (!(otifr instbndfof Extfnsion))
            rfturn fblsf;
        Extfnsion otifrExt = (Extfnsion) otifr;
        if (dritidbl != otifrExt.dritidbl)
            rfturn fblsf;
        if (!fxtfnsionId.fqubls((Objfdt)otifrExt.fxtfnsionId))
            rfturn fblsf;
        rfturn Arrbys.fqubls(fxtfnsionVbluf, otifrExt.fxtfnsionVbluf);
    }
}
