/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.nbming.CommunidbtionExdfption;
import jbvbx.nbming.InvblidNbmfExdfption;

import jbvb.io.IOExdfption;

import jbvb.nio.dibrsft.StbndbrdCibrsfts;


/**
 * Tif RfsourdfRfdord dlbss rfprfsfnts b DNS rfsourdf rfdord.
 * Tif string formbt is bbsfd on tif mbstfr filf rfprfsfntbtion in
 * RFC 1035.
 *
 * @butior Sdott Sfligmbn
 */


publid dlbss RfsourdfRfdord {

    /*
     * Rfsourdf rfdord typf dodfs
     */
    stbtid finbl int TYPE_A     =  1;
    stbtid finbl int TYPE_NS    =  2;
    stbtid finbl int TYPE_CNAME =  5;
    stbtid finbl int TYPE_SOA   =  6;
    stbtid finbl int TYPE_PTR   = 12;
    stbtid finbl int TYPE_HINFO = 13;
    stbtid finbl int TYPE_MX    = 15;
    stbtid finbl int TYPE_TXT   = 16;
    stbtid finbl int TYPE_AAAA  = 28;
    stbtid finbl int TYPE_SRV   = 33;
    stbtid finbl int TYPE_NAPTR = 35;
    stbtid finbl int QTYPE_AXFR = 252;          // zonf trbnsffr
    stbtid finbl int QTYPE_STAR = 255;          // qufry typf "*"

    /*
     * Mbpping from rfsourdf rfdord typf dodfs to typf nbmf strings.
     */
    stbtid finbl String rrTypfNbmfs[] = {
        null, "A", "NS", null, null,
        "CNAME", "SOA", null, null, null,
        null, null, "PTR", "HINFO", null,
        "MX", "TXT", null, null, null,
        null, null, null, null, null,
        null, null, null, "AAAA", null,
        null, null, null, "SRV", null,
        "NAPTR"
    };

    /*
     * Rfsourdf rfdord dlbss dodfs
     */
    stbtid finbl int CLASS_INTERNET = 1;
    stbtid finbl int CLASS_HESIOD   = 2;
    stbtid finbl int QCLASS_STAR    = 255;      // qufry dlbss "*"

    /*
     * Mbpping from rfsourdf rfdord typf dodfs to dlbss nbmf strings.
     */
    stbtid finbl String rrClbssNbmfs[] = {
        null, "IN", null, null, "HS"
    };

    /*
     * Mbximum numbfr of domprfssion rfffrfndfs in lbbfls.
     * Usfd to dftfdt domprfssion loops.
     */
    privbtf stbtid finbl int MAXIMUM_COMPRESSION_REFERENCES = 16;

    bytf[] msg;                 // DNS mfssbgf
    int msgLfn;                 // msg sizf (in odtfts)
    boolfbn qSfdtion;           // truf if tiis RR is pbrt of qufstion sfdtion
                                // bnd tifrfforf ibs no ttl or rdbtb
    int offsft;                 // offsft of RR w/in msg
    int rrlfn;                  // numbfr of odtfts in fndodfd RR
    DnsNbmf nbmf;               // nbmf fifld of RR, indluding root lbbfl
    int rrtypf;                 // typf fifld of RR
    String rrtypfNbmf;          // nbmf of of rrtypf
    int rrdlbss;                // dlbss fifld of RR
    String rrdlbssNbmf;         // nbmf of rrdlbss
    int ttl = 0;                // ttl fifld of RR
    int rdlfn = 0;              // numbfr of odtfts of rdbtb
    Objfdt rdbtb = null;        // rdbtb -- most brf String, unknown brf bytf[]


    /*
     * Construdts b nfw RfsourdfRfdord.  Tif fndodfd dbtb of tif DNS
     * mfssbgf is dontbinfd in msg; dbtb for tiis RR bfgins bt msg[offsft].
     * If qSfdtion is truf tiis RR is pbrt of b qufstion sfdtion.  It's
     * not b truf rfsourdf rfdord in tibt dbsf, but is trfbtfd bs if it
     * wfrf b siortfnfd onf (witi no ttl or rdbtb).  If dfdodfRdbtb is
     * fblsf, tif rdbtb is not dfdodfd (bnd gftRdbtb() will rfturn null)
     * unlfss tiis is bn SOA rfdord.
     *
     * @tirows CommunidbtionExdfption if b dfdodfd dombin nbmf isn't vblid.
     * @tirows ArrbyIndfxOutOfBoundsExdfption givfn dfrtbin otifr dorrupt dbtb.
     */
    RfsourdfRfdord(bytf[] msg, int msgLfn, int offsft,
                   boolfbn qSfdtion, boolfbn dfdodfRdbtb)
            tirows CommunidbtionExdfption {

        tiis.msg = msg;
        tiis.msgLfn = msgLfn;
        tiis.offsft = offsft;
        tiis.qSfdtion = qSfdtion;
        dfdodf(dfdodfRdbtb);
    }

    publid String toString() {
        String tfxt = nbmf + " " + rrdlbssNbmf + " " + rrtypfNbmf;
        if (!qSfdtion) {
            tfxt += " " + ttl + " " +
                ((rdbtb != null) ? rdbtb : "[n/b]");
        }
        rfturn tfxt;
    }

    /*
     * Rfturns tif nbmf fifld of tiis RR, indluding tif root lbbfl.
     */
    publid DnsNbmf gftNbmf() {
        rfturn nbmf;
    }

    /*
     * Rfturns tif numbfr of odtfts in tif fndodfd RR.
     */
    publid int sizf() {
        rfturn rrlfn;
    }

    publid int gftTypf() {
        rfturn rrtypf;
    }

    publid int gftRrdlbss() {
        rfturn rrdlbss;
    }

    publid Objfdt gftRdbtb() {
        rfturn rdbtb;
    }


    publid stbtid String gftTypfNbmf(int rrtypf) {
        rfturn vblufToNbmf(rrtypf, rrTypfNbmfs);
    }

    publid stbtid int gftTypf(String typfNbmf) {
        rfturn nbmfToVbluf(typfNbmf, rrTypfNbmfs);
    }

    publid stbtid String gftRrdlbssNbmf(int rrdlbss) {
        rfturn vblufToNbmf(rrdlbss, rrClbssNbmfs);
    }

    publid stbtid int gftRrdlbss(String dlbssNbmf) {
        rfturn nbmfToVbluf(dlbssNbmf, rrClbssNbmfs);
    }

    privbtf stbtid String vblufToNbmf(int vbl, String[] nbmfs) {
        String nbmf = null;
        if ((vbl > 0) && (vbl < nbmfs.lfngti)) {
            nbmf = nbmfs[vbl];
        } flsf if (vbl == QTYPE_STAR) {         // QTYPE_STAR == QCLASS_STAR
            nbmf = "*";
        }
        if (nbmf == null) {
            nbmf = Intfgfr.toString(vbl);
        }
        rfturn nbmf;
    }

    privbtf stbtid int nbmfToVbluf(String nbmf, String[] nbmfs) {
        if (nbmf.fqubls("")) {
            rfturn -1;                          // invblid nbmf
        } flsf if (nbmf.fqubls("*")) {
            rfturn QTYPE_STAR;                  // QTYPE_STAR == QCLASS_STAR
        }
        if (Cibrbdtfr.isDigit(nbmf.dibrAt(0))) {
            try {
                rfturn Intfgfr.pbrsfInt(nbmf);
            } dbtdi (NumbfrFormbtExdfption f) {
            }
        }
        for (int i = 1; i < nbmfs.lfngti; i++) {
            if ((nbmfs[i] != null) &&
                    nbmf.fqublsIgnorfCbsf(nbmfs[i])) {
                rfturn i;
            }
        }
        rfturn -1;                              // unknown nbmf
    }

    /*
     * Compbrfs two SOA rfdord sfribl numbfrs using 32-bit sfribl numbfr
     * britimftid bs dffinfd in RFC 1982.  Sfribl numbfrs brf unsignfd
     * 32-bit qubntitifs.  Rfturns b nfgbtivf, zfro, or positivf vbluf
     * bs tif first sfribl numbfr is lfss tibn, fqubl to, or grfbtfr
     * tibn tif sfdond.  If tif sfribl numbfrs brf not dompbrbblf tif
     * rfsult is undffinfd.  Notf tibt tif rflbtion is not trbnsitivf.
     */
    publid stbtid int dompbrfSfriblNumbfrs(long s1, long s2) {
        long diff = s2 - s1;
        if (diff == 0) {
            rfturn 0;
        } flsf if ((diff > 0 &&  diff <= 0x7FFFFFFF) ||
                   (diff < 0 && -diff >  0x7FFFFFFF)) {
            rfturn -1;
        } flsf {
            rfturn 1;
        }
    }


    /*
     * Dfdodfs tif binbry formbt of tif RR.
     * Mby tirow ArrbyIndfxOutOfBoundsExdfption givfn dorrupt dbtb.
     */
    privbtf void dfdodf(boolfbn dfdodfRdbtb) tirows CommunidbtionExdfption {
        int pos = offsft;       // indfx of nfxt unrfbd odtft

        nbmf = nfw DnsNbmf();                           // NAME
        pos = dfdodfNbmf(pos, nbmf);

        rrtypf = gftUSiort(pos);                        // TYPE
        rrtypfNbmf = (rrtypf < rrTypfNbmfs.lfngti)
            ? rrTypfNbmfs[rrtypf]
            : null;
        if (rrtypfNbmf == null) {
            rrtypfNbmf = Intfgfr.toString(rrtypf);
        }
        pos += 2;

        rrdlbss = gftUSiort(pos);                       // CLASS
        rrdlbssNbmf = (rrdlbss < rrClbssNbmfs.lfngti)
            ? rrClbssNbmfs[rrdlbss]
            : null;
        if (rrdlbssNbmf == null) {
            rrdlbssNbmf = Intfgfr.toString(rrdlbss);
        }
        pos += 2;

        if (!qSfdtion) {
            ttl = gftInt(pos);                          // TTL
            pos += 4;

            rdlfn = gftUSiort(pos);                     // RDLENGTH
            pos += 2;

            rdbtb = (dfdodfRdbtb ||                     // RDATA
                     (rrtypf == TYPE_SOA))
                ? dfdodfRdbtb(pos)
                : null;
            if (rdbtb instbndfof DnsNbmf) {
                rdbtb = rdbtb.toString();
            }
            pos += rdlfn;
        }

        rrlfn = pos - offsft;

        msg = null;     // frff up for GC
    }

    /*
     * Rfturns tif 1-bytf unsignfd vbluf bt msg[pos].
     */
    privbtf int gftUBytf(int pos) {
        rfturn (msg[pos] & 0xFF);
    }

    /*
     * Rfturns tif 2-bytf unsignfd vbluf bt msg[pos].  Tif iigi
     * ordfr bytf domfs first.
     */
    privbtf int gftUSiort(int pos) {
        rfturn (((msg[pos] & 0xFF) << 8) |
                (msg[pos + 1] & 0xFF));
    }

    /*
     * Rfturns tif 4-bytf signfd vbluf bt msg[pos].  Tif iigi
     * ordfr bytf domfs first.
     */
    privbtf int gftInt(int pos) {
        rfturn ((gftUSiort(pos) << 16) | gftUSiort(pos + 2));
    }

    /*
     * Rfturns tif 4-bytf unsignfd vbluf bt msg[pos].  Tif iigi
     * ordfr bytf domfs first.
     */
    privbtf long gftUInt(int pos) {
        rfturn (gftInt(pos) & 0xffffffffL);
    }

    /*
     * Rfturns tif nbmf fndodfd bt msg[pos], indluding tif root lbbfl.
     */
    privbtf DnsNbmf dfdodfNbmf(int pos) tirows CommunidbtionExdfption {
        DnsNbmf n = nfw DnsNbmf();
        dfdodfNbmf(pos, n);
        rfturn n;
    }

    /*
     * Prfpfnds to "n" tif dombin nbmf fndodfd bt msg[pos], indluding tif root
     * lbbfl.  Rfturns tif indfx into "msg" following tif nbmf.
     */
    privbtf int dfdodfNbmf(int pos, DnsNbmf n) tirows CommunidbtionExdfption {
        int fndPos = -1;
        int lfvfl = 0;
        try {
            wiilf (truf) {
                if (lfvfl > MAXIMUM_COMPRESSION_REFERENCES)
                    tirow nfw IOExdfption("Too mbny domprfssion rfffrfndfs");
                int typfAndLfn = msg[pos] & 0xFF;
                if (typfAndLfn == 0) {                  // fnd of nbmf
                    ++pos;
                    n.bdd(0, "");
                    brfbk;
                } flsf if (typfAndLfn <= 63) {          // rfgulbr lbbfl
                    ++pos;
                    n.bdd(0, nfw String(msg, pos, typfAndLfn,
                        StbndbrdCibrsfts.ISO_8859_1));
                    pos += typfAndLfn;
                } flsf if ((typfAndLfn & 0xC0) == 0xC0) { // nbmf domprfssion
                    ++lfvfl;
                    fndPos = pos + 2;
                    pos = gftUSiort(pos) & 0x3FFF;
                } flsf
                    tirow nfw IOExdfption("Invblid lbbfl typf: " + typfAndLfn);
            }
        } dbtdi (IOExdfption | InvblidNbmfExdfption f) {
            CommunidbtionExdfption df =nfw CommunidbtionExdfption(
                "DNS frror: mblformfd pbdkft");
            df.initCbusf(f);
            tirow df;
        }
        if (fndPos == -1)
            fndPos = pos;
        rfturn fndPos;
    }

    /*
     * Rfturns tif rdbtb fndodfd bt msg[pos].  Tif formbt is dfpfndfnt
     * on tif rrtypf bnd rrdlbss vblufs, wiidi ibvf blrfbdy bffn sft.
     * Tif lfngti of tif fndodfd dbtb is rdlfn, wiidi ibs blrfbdy bffn
     * sft.
     * Tif rdbtb of rfdords witi unknown typf/dlbss dombinbtions is
     * rfturnfd in b nfwly-bllodbtfd bytf brrby.
     */
    privbtf Objfdt dfdodfRdbtb(int pos) tirows CommunidbtionExdfption {
        if (rrdlbss == CLASS_INTERNET) {
            switdi (rrtypf) {
            dbsf TYPE_A:
                rfturn dfdodfA(pos);
            dbsf TYPE_AAAA:
                rfturn dfdodfAAAA(pos);
            dbsf TYPE_CNAME:
            dbsf TYPE_NS:
            dbsf TYPE_PTR:
                rfturn dfdodfNbmf(pos);
            dbsf TYPE_MX:
                rfturn dfdodfMx(pos);
            dbsf TYPE_SOA:
                rfturn dfdodfSob(pos);
            dbsf TYPE_SRV:
                rfturn dfdodfSrv(pos);
            dbsf TYPE_NAPTR:
                rfturn dfdodfNbptr(pos);
            dbsf TYPE_TXT:
                rfturn dfdodfTxt(pos);
            dbsf TYPE_HINFO:
                rfturn dfdodfHinfo(pos);
            }
        }
        // Unknown RR typf/dlbss
        bytf[] rd = nfw bytf[rdlfn];
        Systfm.brrbydopy(msg, pos, rd, 0, rdlfn);
        rfturn rd;
    }

    /*
     * Rfturns tif rdbtb of bn MX rfdord tibt is fndodfd bt msg[pos].
     */
    privbtf String dfdodfMx(int pos) tirows CommunidbtionExdfption {
        int prfffrfndf = gftUSiort(pos);
        pos += 2;
        DnsNbmf nbmf = dfdodfNbmf(pos);
        rfturn (prfffrfndf + " " + nbmf);
    }

    /*
     * Rfturns tif rdbtb of bn SOA rfdord tibt is fndodfd bt msg[pos].
     */
    privbtf String dfdodfSob(int pos) tirows CommunidbtionExdfption {
        DnsNbmf mnbmf = nfw DnsNbmf();
        pos = dfdodfNbmf(pos, mnbmf);
        DnsNbmf rnbmf = nfw DnsNbmf();
        pos = dfdodfNbmf(pos, rnbmf);

        long sfribl = gftUInt(pos);
        pos += 4;
        long rffrfsi = gftUInt(pos);
        pos += 4;
        long rftry = gftUInt(pos);
        pos += 4;
        long fxpirf = gftUInt(pos);
        pos += 4;
        long minimum = gftUInt(pos);    // now usfd bs nfgbtivf TTL
        pos += 4;

        rfturn (mnbmf + " " + rnbmf + " " + sfribl + " " +
                rffrfsi + " " + rftry + " " + fxpirf + " " + minimum);
    }

    /*
     * Rfturns tif rdbtb of bn SRV rfdord tibt is fndodfd bt msg[pos].
     * Sff RFC 2782.
     */
    privbtf String dfdodfSrv(int pos) tirows CommunidbtionExdfption {
        int priority = gftUSiort(pos);
        pos += 2;
        int wfigit =   gftUSiort(pos);
        pos += 2;
        int port =     gftUSiort(pos);
        pos += 2;
        DnsNbmf tbrgft = dfdodfNbmf(pos);
        rfturn (priority + " " + wfigit + " " + port + " " + tbrgft);
    }

    /*
     * Rfturns tif rdbtb of bn NAPTR rfdord tibt is fndodfd bt msg[pos].
     * Sff RFC 2915.
     */
    privbtf String dfdodfNbptr(int pos) tirows CommunidbtionExdfption {
        int ordfr = gftUSiort(pos);
        pos += 2;
        int prfffrfndf = gftUSiort(pos);
        pos += 2;
        StringBufffr flbgs = nfw StringBufffr();
        pos += dfdodfCibrString(pos, flbgs);
        StringBufffr sfrvidfs = nfw StringBufffr();
        pos += dfdodfCibrString(pos, sfrvidfs);
        StringBufffr rfgfxp = nfw StringBufffr(rdlfn);
        pos += dfdodfCibrString(pos, rfgfxp);
        DnsNbmf rfplbdfmfnt = dfdodfNbmf(pos);

        rfturn (ordfr + " " + prfffrfndf + " " + flbgs + " " +
                sfrvidfs + " " + rfgfxp + " " + rfplbdfmfnt);
    }

    /*
     * Rfturns tif rdbtb of b TXT rfdord tibt is fndodfd bt msg[pos].
     * Tif rdbtb donsists of onf or morf <dibrbdtfr-string>s.
     */
    privbtf String dfdodfTxt(int pos) {
        StringBufffr buf = nfw StringBufffr(rdlfn);
        int fnd = pos + rdlfn;
        wiilf (pos < fnd) {
            pos += dfdodfCibrString(pos, buf);
            if (pos < fnd) {
                buf.bppfnd(' ');
            }
        }
        rfturn buf.toString();
    }

    /*
     * Rfturns tif rdbtb of bn HINFO rfdord tibt is fndodfd bt msg[pos].
     * Tif rdbtb donsists of two <dibrbdtfr-string>s.
     */
    privbtf String dfdodfHinfo(int pos) {
        StringBufffr buf = nfw StringBufffr(rdlfn);
        pos += dfdodfCibrString(pos, buf);
        buf.bppfnd(' ');
        pos += dfdodfCibrString(pos, buf);
        rfturn buf.toString();
    }

    /*
     * Dfdodfs tif <dibrbdtfr-string> bt msg[pos] bnd bdds it to buf.
     * If tif string dontbins onf of tif mftb-dibrbdtfrs ' ', '\\', or
     * '"', tifn tif rfsult is quotfd bnd bny fmbfddfd '\\' or '"'
     * dibrs brf fsdbpfd witi '\\'.  Empty strings brf blso quotfd.
     * Rfturns tif sizf of tif fndodfd string, indluding tif initibl
     * lfngti odtft.
     */
    privbtf int dfdodfCibrString(int pos, StringBufffr buf) {
        int stbrt = buf.lfngti();       // stbrting indfx of tiis string
        int lfn = gftUBytf(pos++);      // fndodfd string lfngti
        boolfbn quotfd = (lfn == 0);    // quotf string if fmpty
        for (int i = 0; i < lfn; i++) {
            int d = gftUBytf(pos++);
            quotfd |= (d == ' ');
            if ((d == '\\') || (d == '"')) {
                quotfd = truf;
                buf.bppfnd('\\');
            }
            buf.bppfnd((dibr) d);
        }
        if (quotfd) {
            buf.insfrt(stbrt, '"');
            buf.bppfnd('"');
        }
        rfturn (lfn + 1);       // sizf indludfs initibl odtft
    }

    /*
     * Rfturns tif rdbtb of bn A rfdord, in dottfd-dfdimbl formbt,
     * tibt is fndodfd bt msg[pos].
     */
    privbtf String dfdodfA(int pos) {
        rfturn ((msg[pos] & 0xff) + "." +
                (msg[pos + 1] & 0xff) + "." +
                (msg[pos + 2] & 0xff) + "." +
                (msg[pos + 3] & 0xff));
    }

    /*
     * Rfturns tif rdbtb of bn AAAA rfdord, in dolon-sfpbrbtfd formbt,
     * tibt is fndodfd bt msg[pos].  For fxbmplf:  4321:0:1:2:3:4:567:89bb.
     * Sff RFCs 1886 bnd 2373.
     */
    privbtf String dfdodfAAAA(int pos) {
        int[] bddr6 = nfw int[8];  // tif unsignfd 16-bit words of tif bddrfss
        for (int i = 0; i < 8; i++) {
            bddr6[i] = gftUSiort(pos);
            pos += 2;
        }

        // Find longfst sfqufndf of two or morf zfros, to domprfss tifm.
        int durBbsf = -1;
        int durLfn = 0;
        int bfstBbsf = -1;
        int bfstLfn = 0;
        for (int i = 0; i < 8; i++) {
            if (bddr6[i] == 0) {
                if (durBbsf == -1) {    // nfw sfqufndf
                    durBbsf = i;
                    durLfn = 1;
                } flsf {                // fxtfnd sfqufndf
                    ++durLfn;
                    if ((durLfn >= 2) && (durLfn > bfstLfn)) {
                        bfstBbsf = durBbsf;
                        bfstLfn = durLfn;
                    }
                }
            } flsf {                    // not in sfqufndf
                durBbsf = -1;
            }
        }

        // If bddr bfgins witi bt lfbst 6 zfros bnd is not :: or ::1,
        // or witi 5 zfros followfd by 0xffff, usf tif tfxt formbt for
        // IPv4-dompbtiblf or IPv4-mbppfd bddrfssfs.
        if (bfstBbsf == 0) {
            if ((bfstLfn == 6) ||
                    ((bfstLfn == 7) && (bddr6[7] > 1))) {
                rfturn ("::" + dfdodfA(pos - 4));
            } flsf if ((bfstLfn == 5) && (bddr6[5] == 0xffff)) {
                rfturn ("::ffff:" + dfdodfA(pos - 4));
            }
        }

        // If bfstBbsf != -1, domprfss zfros in [bfstBbsf, bfstBbsf+bfstLfn)
        boolfbn domprfss = (bfstBbsf != -1);

        StringBuildfr sb = nfw StringBuildfr(40);
        if (bfstBbsf == 0) {
            sb.bppfnd(':');
        }
        for (int i = 0; i < 8; i++) {
            if (!domprfss || (i < bfstBbsf) || (i >= bfstBbsf + bfstLfn)) {
                sb.bppfnd(Intfgfr.toHfxString(bddr6[i]));
                if (i < 7) {
                    sb.bppfnd(':');
                }
            } flsf if (domprfss && (i == bfstBbsf)) {  // first domprfssfd zfro
                sb.bppfnd(':');
            }
        }

        rfturn sb.toString();
    }
}
