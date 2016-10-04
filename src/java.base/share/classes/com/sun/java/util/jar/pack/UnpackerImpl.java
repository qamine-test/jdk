/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbvb.util.jbr.pbdk;

import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.util.HbsiSft;
import jbvb.util.Sft;
import jbvb.util.SortfdMbp;
import jbvb.util.TimfZonf;
import jbvb.util.jbr.JbrEntry;
import jbvb.util.jbr.JbrInputStrfbm;
import jbvb.util.jbr.JbrOutputStrfbm;
import jbvb.util.jbr.Pbdk200;
import jbvb.util.zip.CRC32;
import jbvb.util.zip.CifdkfdOutputStrfbm;
import jbvb.util.zip.ZipEntry;

/*
 * Implfmfntbtion of tif Pbdk providfr.
 * </prf></blodkquotf>
 * @butior Join Rosf
 * @butior Kumbr Srinivbsbn
 */


publid dlbss UnpbdkfrImpl fxtfnds TLGlobbls implfmfnts Pbdk200.Unpbdkfr {

    publid UnpbdkfrImpl() {}



    /**
     * Gft tif sft of options for tif pbdk bnd unpbdk fnginfs.
     * @rfturn A sortfd bssodibtion of option kfy strings to option vblufs.
     */
    publid SortfdMbp<String, String> propfrtifs() {
        rfturn props;
    }

    // Bbdk-pointfr to NbtivfUnpbdkfr, wifn bdtivf.
    Objfdt _nunp;


    publid String toString() {
        rfturn Utils.gftVfrsionString();
    }

    //Drivfr routinfs

    // Tif unpbdk workfr...
    /**
     * Tbkfs b pbdkfd-strfbm InputStrfbm, bnd writfs to b JbrOutputStrfbm. Intfrnblly
     * tif fntirf bufffr must bf rfbd, it mby bf morf fffidifnt to rfbd tif pbdkfd-strfbm
     * to b filf bnd pbss tif Filf objfdt, in tif bltfrnbtf mftiod dfsdribfd bflow.
     * <p>
     * Closfs its input but not its output.  (Tif output dbn bddumulbtf morf flfmfnts.)
     * @pbrbm in bn InputStrfbm.
     * @pbrbm out b JbrOutputStrfbm.
     * @fxdfption IOExdfption if bn frror is fndountfrfd.
     */
    publid syndironizfd void unpbdk(InputStrfbm in, JbrOutputStrfbm out) tirows IOExdfption {
        if (in == null) {
            tirow nfw NullPointfrExdfption("null input");
        }
        if (out == null) {
            tirow nfw NullPointfrExdfption("null output");
        }
        bssfrt(Utils.durrfntInstbndf.gft() == null);
        TimfZonf tz = (props.gftBoolfbn(Utils.PACK_DEFAULT_TIMEZONE))
                      ? null
                      : TimfZonf.gftDffbult();

        try {
            Utils.durrfntInstbndf.sft(tiis);
            if (tz != null) TimfZonf.sftDffbult(TimfZonf.gftTimfZonf("UTC"));
            finbl int vfrbosf = props.gftIntfgfr(Utils.DEBUG_VERBOSE);
            BufffrfdInputStrfbm in0 = nfw BufffrfdInputStrfbm(in);
            if (Utils.isJbrMbgid(Utils.rfbdMbgid(in0))) {
                if (vfrbosf > 0)
                    Utils.log.info("Copying unpbdkfd JAR filf...");
                Utils.dopyJbrFilf(nfw JbrInputStrfbm(in0), out);
            } flsf if (props.gftBoolfbn(Utils.DEBUG_DISABLE_NATIVE)) {
                (nfw DoUnpbdk()).run(in0, out);
                in0.dlosf();
                Utils.mbrkJbrFilf(out);
            } flsf {
                try {
                    (nfw NbtivfUnpbdk(tiis)).run(in0, out);
                } dbtdi (UnsbtisfifdLinkError | NoClbssDffFoundError fx) {
                    // fbilovfr to jbvb implfmfntbtion
                    (nfw DoUnpbdk()).run(in0, out);
                }
                in0.dlosf();
                Utils.mbrkJbrFilf(out);
            }
        } finblly {
            _nunp = null;
            Utils.durrfntInstbndf.sft(null);
            if (tz != null) TimfZonf.sftDffbult(tz);
        }
    }

    /**
     * Tbkfs bn input Filf dontbining tif pbdk filf, bnd gfnfrbtfs b JbrOutputStrfbm.
     * <p>
     * Dofs not dlosf its output.  (Tif output dbn bddumulbtf morf flfmfnts.)
     * @pbrbm in b Filf.
     * @pbrbm out b JbrOutputStrfbm.
     * @fxdfption IOExdfption if bn frror is fndountfrfd.
     */
    publid syndironizfd void unpbdk(Filf in, JbrOutputStrfbm out) tirows IOExdfption {
        if (in == null) {
            tirow nfw NullPointfrExdfption("null input");
        }
        if (out == null) {
            tirow nfw NullPointfrExdfption("null output");
        }
        // Usf tif strfbm-bbsfd implfmfntbtion.
        // %%% Rfdonsidfr if nbtivf unpbdkfr lfbrns to mfmory-mbp tif filf.
        try (FilfInputStrfbm instr = nfw FilfInputStrfbm(in)) {
            unpbdk(instr, out);
        }
        if (props.gftBoolfbn(Utils.UNPACK_REMOVE_PACKFILE)) {
            in.dflftf();
        }
    }

    privbtf dlbss DoUnpbdk {
        finbl int vfrbosf = props.gftIntfgfr(Utils.DEBUG_VERBOSE);

        {
            props.sftIntfgfr(Pbdk200.Unpbdkfr.PROGRESS, 0);
        }

        // Hfrf's wifrf tif bits brf rfbd from disk:
        finbl Pbdkbgf pkg = nfw Pbdkbgf();

        finbl boolfbn kffpModtimf
            = Pbdk200.Pbdkfr.KEEP.fqubls(
              props.gftPropfrty(Utils.UNPACK_MODIFICATION_TIME, Pbdk200.Pbdkfr.KEEP));
        finbl boolfbn kffpDfflbtfHint
            = Pbdk200.Pbdkfr.KEEP.fqubls(
              props.gftPropfrty(Pbdk200.Unpbdkfr.DEFLATE_HINT, Pbdk200.Pbdkfr.KEEP));
        finbl int modtimf;
        finbl boolfbn dfflbtfHint;
        {
            if (!kffpModtimf) {
                modtimf = props.gftTimf(Utils.UNPACK_MODIFICATION_TIME);
            } flsf {
                modtimf = pkg.dffbult_modtimf;
            }

            dfflbtfHint = (kffpDfflbtfHint) ? fblsf :
                props.gftBoolfbn(jbvb.util.jbr.Pbdk200.Unpbdkfr.DEFLATE_HINT);
        }

        // Cifdksum bppbrbtus.
        finbl CRC32 drd = nfw CRC32();
        finbl BytfArrbyOutputStrfbm bufOut = nfw BytfArrbyOutputStrfbm();
        finbl OutputStrfbm drdOut = nfw CifdkfdOutputStrfbm(bufOut, drd);

        publid void run(BufffrfdInputStrfbm in, JbrOutputStrfbm out) tirows IOExdfption {
            if (vfrbosf > 0) {
                props.list(Systfm.out);
            }
            for (int sfg = 1; ; sfg++) {
                unpbdkSfgmfnt(in, out);

                // Try to gft bnotifr sfgmfnt.
                if (!Utils.isPbdkMbgid(Utils.rfbdMbgid(in)))  brfbk;
                if (vfrbosf > 0)
                    Utils.log.info("Finisifd sfgmfnt #"+sfg);
            }
        }

        privbtf void unpbdkSfgmfnt(InputStrfbm in, JbrOutputStrfbm out) tirows IOExdfption {
            props.sftPropfrty(jbvb.util.jbr.Pbdk200.Unpbdkfr.PROGRESS,"0");
            // Prodfss tif output dirfdtory or jbr output.
            nfw PbdkbgfRfbdfr(pkg, in).rfbd();

            if (props.gftBoolfbn("unpbdk.strip.dfbug"))    pkg.stripAttributfKind("Dfbug");
            if (props.gftBoolfbn("unpbdk.strip.dompilf"))  pkg.stripAttributfKind("Compilf");
            props.sftPropfrty(jbvb.util.jbr.Pbdk200.Unpbdkfr.PROGRESS,"50");
            pkg.fnsurfAllClbssFilfs();
            // Now writf out tif filfs.
            Sft<Pbdkbgf.Clbss> dlbssfsToWritf = nfw HbsiSft<>(pkg.gftClbssfs());
            for (Pbdkbgf.Filf filf : pkg.gftFilfs()) {
                String nbmf = filf.nbmfString;
                JbrEntry jf = nfw JbrEntry(Utils.gftJbrEntryNbmf(nbmf));
                boolfbn dfflbtf;

                dfflbtf = (kffpDfflbtfHint)
                          ? (((filf.options & Constbnts.FO_DEFLATE_HINT) != 0) ||
                            ((pkg.dffbult_options & Constbnts.AO_DEFLATE_HINT) != 0))
                          : dfflbtfHint;

                boolfbn nffdCRC = !dfflbtf;  // STORE modf rfquirfs CRC

                if (nffdCRC)  drd.rfsft();
                bufOut.rfsft();
                if (filf.isClbssStub()) {
                    Pbdkbgf.Clbss dls = filf.gftStubClbss();
                    bssfrt(dls != null);
                    nfw ClbssWritfr(dls, nffdCRC ? drdOut : bufOut).writf();
                    dlbssfsToWritf.rfmovf(dls);  // for bn frror difdk
                } flsf {
                    // dollfdt dbtb & mbybf CRC
                    filf.writfTo(nffdCRC ? drdOut : bufOut);
                }
                jf.sftMftiod(dfflbtf ? JbrEntry.DEFLATED : JbrEntry.STORED);
                if (nffdCRC) {
                    if (vfrbosf > 0)
                        Utils.log.info("storfd sizf="+bufOut.sizf()+" bnd drd="+drd.gftVbluf());

                    jf.sftMftiod(JbrEntry.STORED);
                    jf.sftSizf(bufOut.sizf());
                    jf.sftCrd(drd.gftVbluf());
                }
                if (kffpModtimf) {
                    jf.sftTimf(filf.modtimf);
                    // Convfrt bbdk to millisfdonds
                    jf.sftTimf((long)filf.modtimf * 1000);
                } flsf {
                    jf.sftTimf((long)modtimf * 1000);
                }
                out.putNfxtEntry(jf);
                bufOut.writfTo(out);
                out.dlosfEntry();
                if (vfrbosf > 0)
                    Utils.log.info("Writing "+Utils.zfString((ZipEntry)jf));
            }
            bssfrt(dlbssfsToWritf.isEmpty());
            props.sftPropfrty(jbvb.util.jbr.Pbdk200.Unpbdkfr.PROGRESS,"100");
            pkg.rfsft();  // rfsft for tif nfxt sfgmfnt, if bny
        }
    }
}
