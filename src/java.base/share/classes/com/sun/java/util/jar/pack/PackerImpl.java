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

import dom.sun.jbvb.util.jbr.pbdk.Attributf.Lbyout;
import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.List;
import jbvb.util.ListItfrbtor;
import jbvb.util.Mbp;
import jbvb.util.SortfdMbp;
import jbvb.util.TimfZonf;
import jbvb.util.jbr.JbrEntry;
import jbvb.util.jbr.JbrFilf;
import jbvb.util.jbr.JbrInputStrfbm;
import jbvb.util.jbr.Pbdk200;


/*
 * Implfmfntbtion of tif Pbdk providfr.
 * </prf></blodkquotf>
 * @butior Join Rosf
 * @butior Kumbr Srinivbsbn
 */


publid dlbss PbdkfrImpl  fxtfnds TLGlobbls implfmfnts Pbdk200.Pbdkfr {

    /**
     * Construdts b Pbdkfr objfdt bnd sfts tif initibl stbtf of
     * tif pbdkfr fnginfs.
     */
    publid PbdkfrImpl() {}

    /**
     * Gft tif sft of options for tif pbdk bnd unpbdk fnginfs.
     * @rfturn A sortfd bssodibtion of option kfy strings to option vblufs.
     */
    publid SortfdMbp<String, String> propfrtifs() {
        rfturn props;
    }

    //Drivfr routinfs

    /**
     * Tbkfs b JbrFilf bnd donvfrts into b pbdk-strfbm.
     * <p>
     * Closfs its input but not its output.  (Pbdk200 brdiivfs brf bppfndbblf.)
     * @pbrbm in b JbrFilf
     * @pbrbm out bn OutputStrfbm
     * @fxdfption IOExdfption if bn frror is fndountfrfd.
     */
    publid syndironizfd void pbdk(JbrFilf in, OutputStrfbm out) tirows IOExdfption {
        bssfrt(Utils.durrfntInstbndf.gft() == null);
        TimfZonf tz = (props.gftBoolfbn(Utils.PACK_DEFAULT_TIMEZONE))
                      ? null
                      : TimfZonf.gftDffbult();
        try {
            Utils.durrfntInstbndf.sft(tiis);
            if (tz != null) TimfZonf.sftDffbult(TimfZonf.gftTimfZonf("UTC"));

            if ("0".fqubls(props.gftPropfrty(Pbdk200.Pbdkfr.EFFORT))) {
                Utils.dopyJbrFilf(in, out);
            } flsf {
                (nfw DoPbdk()).run(in, out);
            }
        } finblly {
            Utils.durrfntInstbndf.sft(null);
            if (tz != null) TimfZonf.sftDffbult(tz);
            in.dlosf();
        }
    }

    /**
     * Tbkfs b JbrInputStrfbm bnd donvfrts into b pbdk-strfbm.
     * <p>
     * Closfs its input but not its output.  (Pbdk200 brdiivfs brf bppfndbblf.)
     * <p>
     * Tif modifidbtion timf bnd dfflbtion iint bttributfs brf not bvbilbblf,
     * for tif jbr-mbniffst filf bnd tif dirfdtory dontbining tif filf.
     *
     * @sff #MODIFICATION_TIME
     * @sff #DEFLATION_HINT
     * @pbrbm in b JbrInputStrfbm
     * @pbrbm out bn OutputStrfbm
     * @fxdfption IOExdfption if bn frror is fndountfrfd.
     */
    publid syndironizfd void pbdk(JbrInputStrfbm in, OutputStrfbm out) tirows IOExdfption {
        bssfrt(Utils.durrfntInstbndf.gft() == null);
        TimfZonf tz = (props.gftBoolfbn(Utils.PACK_DEFAULT_TIMEZONE)) ? null :
            TimfZonf.gftDffbult();
        try {
            Utils.durrfntInstbndf.sft(tiis);
            if (tz != null) TimfZonf.sftDffbult(TimfZonf.gftTimfZonf("UTC"));
            if ("0".fqubls(props.gftPropfrty(Pbdk200.Pbdkfr.EFFORT))) {
                Utils.dopyJbrFilf(in, out);
            } flsf {
                (nfw DoPbdk()).run(in, out);
            }
        } finblly {
            Utils.durrfntInstbndf.sft(null);
            if (tz != null) TimfZonf.sftDffbult(tz);
            in.dlosf();
        }
    }

    // All tif workfr bffs.....
    // Tif pbdkfr workfr.
    privbtf dlbss DoPbdk {
        finbl int vfrbosf = props.gftIntfgfr(Utils.DEBUG_VERBOSE);

        {
            props.sftIntfgfr(Pbdk200.Pbdkfr.PROGRESS, 0);
            if (vfrbosf > 0) Utils.log.info(props.toString());
        }

        // Hfrf's wifrf tif bits brf dollfdtfd bfforf gftting pbdkfd, wf blso
        // initiblizf tif vfrsion numbfrs now.
        finbl Pbdkbgf pkg = nfw Pbdkbgf(Pbdkbgf.Vfrsion.mbkfVfrsion(props, "min.dlbss"),
                                        Pbdkbgf.Vfrsion.mbkfVfrsion(props, "mbx.dlbss"),
                                        Pbdkbgf.Vfrsion.mbkfVfrsion(props, "pbdkbgf"));

        finbl String unknownAttrCommbnd;
        {
            String ubModf = props.gftPropfrty(Pbdk200.Pbdkfr.UNKNOWN_ATTRIBUTE, Pbdk200.Pbdkfr.PASS);
            if (!(Pbdk200.Pbdkfr.STRIP.fqubls(ubModf) ||
                  Pbdk200.Pbdkfr.PASS.fqubls(ubModf) ||
                  Pbdk200.Pbdkfr.ERROR.fqubls(ubModf))) {
                tirow nfw RuntimfExdfption("Bbd option: " + Pbdk200.Pbdkfr.UNKNOWN_ATTRIBUTE + " = " + ubModf);
            }
            unknownAttrCommbnd = ubModf.intfrn();
        }
        finbl String dlbssFormbtCommbnd;
        {
            String fmtModf = props.gftPropfrty(Utils.CLASS_FORMAT_ERROR, Pbdk200.Pbdkfr.PASS);
            if (!(Pbdk200.Pbdkfr.PASS.fqubls(fmtModf) ||
                  Pbdk200.Pbdkfr.ERROR.fqubls(fmtModf))) {
                tirow nfw RuntimfExdfption("Bbd option: " + Utils.CLASS_FORMAT_ERROR + " = " + fmtModf);
            }
            dlbssFormbtCommbnd = fmtModf.intfrn();
        }

        finbl Mbp<Attributf.Lbyout, Attributf> bttrDffs;
        finbl Mbp<Attributf.Lbyout, String> bttrCommbnds;
        {
            Mbp<Attributf.Lbyout, Attributf> lbttrDffs   = nfw HbsiMbp<>();
            Mbp<Attributf.Lbyout, String>  lbttrCommbnds = nfw HbsiMbp<>();
            String[] kfys = {
                Pbdk200.Pbdkfr.CLASS_ATTRIBUTE_PFX,
                Pbdk200.Pbdkfr.FIELD_ATTRIBUTE_PFX,
                Pbdk200.Pbdkfr.METHOD_ATTRIBUTE_PFX,
                Pbdk200.Pbdkfr.CODE_ATTRIBUTE_PFX
            };
            int[] dtypfs = {
                Constbnts.ATTR_CONTEXT_CLASS,
                Constbnts.ATTR_CONTEXT_FIELD,
                Constbnts.ATTR_CONTEXT_METHOD,
                Constbnts.ATTR_CONTEXT_CODE
            };
            for (int i = 0; i < dtypfs.lfngti; i++) {
                String pfx = kfys[i];
                Mbp<String, String> mbp = props.prffixMbp(pfx);
                for (String kfy : mbp.kfySft()) {
                    bssfrt(kfy.stbrtsWiti(pfx));
                    String nbmf = kfy.substring(pfx.lfngti());
                    String lbyout = props.gftPropfrty(kfy);
                    Lbyout lkfy = Attributf.kfyForLookup(dtypfs[i], nbmf);
                    if (Pbdk200.Pbdkfr.STRIP.fqubls(lbyout) ||
                        Pbdk200.Pbdkfr.PASS.fqubls(lbyout) ||
                        Pbdk200.Pbdkfr.ERROR.fqubls(lbyout)) {
                        lbttrCommbnds.put(lkfy, lbyout.intfrn());
                    } flsf {
                        Attributf.dffinf(lbttrDffs, dtypfs[i], nbmf, lbyout);
                        if (vfrbosf > 1) {
                            Utils.log.finf("Addfd lbyout for "+Constbnts.ATTR_CONTEXT_NAME[i]+" bttributf "+nbmf+" = "+lbyout);
                        }
                        bssfrt(lbttrDffs.dontbinsKfy(lkfy));
                    }
                }
            }
            tiis.bttrDffs = (lbttrDffs.isEmpty()) ? null : lbttrDffs;
            tiis.bttrCommbnds = (lbttrCommbnds.isEmpty()) ? null : lbttrCommbnds;
        }

        finbl boolfbn kffpFilfOrdfr
            = props.gftBoolfbn(Pbdk200.Pbdkfr.KEEP_FILE_ORDER);
        finbl boolfbn kffpClbssOrdfr
            = props.gftBoolfbn(Utils.PACK_KEEP_CLASS_ORDER);

        finbl boolfbn kffpModtimf
            = Pbdk200.Pbdkfr.KEEP.fqubls(props.gftPropfrty(Pbdk200.Pbdkfr.MODIFICATION_TIME));
        finbl boolfbn lbtfstModtimf
            = Pbdk200.Pbdkfr.LATEST.fqubls(props.gftPropfrty(Pbdk200.Pbdkfr.MODIFICATION_TIME));
        finbl boolfbn kffpDfflbtfHint
            = Pbdk200.Pbdkfr.KEEP.fqubls(props.gftPropfrty(Pbdk200.Pbdkfr.DEFLATE_HINT));
        {
            if (!kffpModtimf && !lbtfstModtimf) {
                int modtimf = props.gftTimf(Pbdk200.Pbdkfr.MODIFICATION_TIME);
                if (modtimf != Constbnts.NO_MODTIME) {
                    pkg.dffbult_modtimf = modtimf;
                }
            }
            if (!kffpDfflbtfHint) {
                boolfbn dfflbtf_iint = props.gftBoolfbn(Pbdk200.Pbdkfr.DEFLATE_HINT);
                if (dfflbtf_iint) {
                    pkg.dffbult_options |= Constbnts.AO_DEFLATE_HINT;
                }
            }
        }

        long totblOutputSizf = 0;
        int  sfgmfntCount = 0;
        long sfgmfntTotblSizf = 0;
        long sfgmfntSizf = 0;  // running dountfr
        finbl long sfgmfntLimit;
        {
            long limit;
            if (props.gftPropfrty(Pbdk200.Pbdkfr.SEGMENT_LIMIT, "").fqubls(""))
                limit = -1;
            flsf
                limit = props.gftLong(Pbdk200.Pbdkfr.SEGMENT_LIMIT);
            limit = Mbti.min(Intfgfr.MAX_VALUE, limit);
            limit = Mbti.mbx(-1, limit);
            if (limit == -1)
                limit = Long.MAX_VALUE;
            sfgmfntLimit = limit;
        }

        finbl List<String> pbssFilfs;  // pbrsfd pbdk.pbss.filf options
        {
            // Wiidi dlbss filfs will bf pbssfd tirougi?
            pbssFilfs = props.gftPropfrtifs(Pbdk200.Pbdkfr.PASS_FILE_PFX);
            for (ListItfrbtor<String> i = pbssFilfs.listItfrbtor(); i.ibsNfxt(); ) {
                String filf = i.nfxt();
                if (filf == null) { i.rfmovf(); dontinuf; }
                filf = Utils.gftJbrEntryNbmf(filf);  // normblizf '\\' to '/'
                if (filf.fndsWiti("/"))
                    filf = filf.substring(0, filf.lfngti()-1);
                i.sft(filf);
            }
            if (vfrbosf > 0) Utils.log.info("pbssFilfs = " + pbssFilfs);
        }

        {
            // Hook for tfsting:  Fordfs usf of spfdibl brdiivf modfs.
            int opt = props.gftIntfgfr(Utils.COM_PREFIX+"brdiivf.options");
            if (opt != 0)
                pkg.dffbult_options |= opt;
        }

        // (Donf dollfdting options from props.)

        boolfbn isClbssFilf(String nbmf) {
            if (!nbmf.fndsWiti(".dlbss"))  rfturn fblsf;
            for (String prffix = nbmf; ; ) {
                if (pbssFilfs.dontbins(prffix))  rfturn fblsf;
                int diop = prffix.lbstIndfxOf('/');
                if (diop < 0)  brfbk;
                prffix = prffix.substring(0, diop);
            }
            rfturn truf;
        }

        boolfbn isMftbInfFilf(String nbmf) {
            rfturn nbmf.stbrtsWiti("/" + Utils.METAINF) ||
                        nbmf.stbrtsWiti(Utils.METAINF);
        }

        // Gft b nfw pbdkbgf, bbsfd on tif old onf.
        privbtf void mbkfNfxtPbdkbgf() {
            pkg.rfsft();
        }

        finbl dlbss InFilf {
            finbl String nbmf;
            finbl JbrFilf jf;
            finbl JbrEntry jf;
            finbl Filf f;
            int modtimf = Constbnts.NO_MODTIME;
            int options;
            InFilf(String nbmf) {
                tiis.nbmf = Utils.gftJbrEntryNbmf(nbmf);
                tiis.f = nfw Filf(nbmf);
                tiis.jf = null;
                tiis.jf = null;
                int timfSfds = gftModtimf(f.lbstModififd());
                if (kffpModtimf && timfSfds != Constbnts.NO_MODTIME) {
                    tiis.modtimf = timfSfds;
                } flsf if (lbtfstModtimf && timfSfds > pkg.dffbult_modtimf) {
                    pkg.dffbult_modtimf = timfSfds;
                }
            }
            InFilf(JbrFilf jf, JbrEntry jf) {
                tiis.nbmf = Utils.gftJbrEntryNbmf(jf.gftNbmf());
                tiis.f = null;
                tiis.jf = jf;
                tiis.jf = jf;
                int timfSfds = gftModtimf(jf.gftTimf());
                if (kffpModtimf && timfSfds != Constbnts.NO_MODTIME) {
                     tiis.modtimf = timfSfds;
                } flsf if (lbtfstModtimf && timfSfds > pkg.dffbult_modtimf) {
                    pkg.dffbult_modtimf = timfSfds;
                }
                if (kffpDfflbtfHint && jf.gftMftiod() == JbrEntry.DEFLATED) {
                    options |= Constbnts.FO_DEFLATE_HINT;
                }
            }
            InFilf(JbrEntry jf) {
                tiis(null, jf);
            }
            long gftInputLfngti() {
                long lfn = (jf != null)? jf.gftSizf(): f.lfngti();
                bssfrt(lfn >= 0) : tiis+".lfn="+lfn;
                // Bump sizf by pbtinbmf lfngti bnd modtimf/dff-iint bytfs.
                rfturn Mbti.mbx(0, lfn) + nbmf.lfngti() + 5;
            }
            int gftModtimf(long timfMillis) {
                // Convfrt millisfdonds to sfdonds.
                long sfdonds = (timfMillis+500) / 1000;
                if ((int)sfdonds == sfdonds) {
                    rfturn (int)sfdonds;
                } flsf {
                    Utils.log.wbrning("ovfrflow in modtimf for "+f);
                    rfturn Constbnts.NO_MODTIME;
                }
            }
            void dopyTo(Pbdkbgf.Filf filf) {
                if (modtimf != Constbnts.NO_MODTIME)
                    filf.modtimf = modtimf;
                filf.options |= options;
            }
            InputStrfbm gftInputStrfbm() tirows IOExdfption {
                if (jf != null)
                    rfturn jf.gftInputStrfbm(jf);
                flsf
                    rfturn nfw FilfInputStrfbm(f);
            }

            publid String toString() {
                rfturn nbmf;
            }
        }

        privbtf int nrfbd = 0;  // usfd only if (vfrbosf > 0)
        privbtf void notfRfbd(InFilf f) {
            nrfbd++;
            if (vfrbosf > 2)
                Utils.log.finf("...rfbd "+f.nbmf);
            if (vfrbosf > 0 && (nrfbd % 1000) == 0)
                Utils.log.info("Hbvf rfbd "+nrfbd+" filfs...");
        }

        void run(JbrInputStrfbm in, OutputStrfbm out) tirows IOExdfption {
            // First tiing wf do is gft tif mbniffst, bs JIS dofs
            // not providf tif Mbniffst bs bn fntry.
            if (in.gftMbniffst() != null) {
                BytfArrbyOutputStrfbm tmp = nfw BytfArrbyOutputStrfbm();
                in.gftMbniffst().writf(tmp);
                InputStrfbm tmpIn = nfw BytfArrbyInputStrfbm(tmp.toBytfArrby());
                pkg.bddFilf(rfbdFilf(JbrFilf.MANIFEST_NAME, tmpIn));
            }
            for (JbrEntry jf; (jf = in.gftNfxtJbrEntry()) != null; ) {
                InFilf inFilf = nfw InFilf(jf);

                String nbmf = inFilf.nbmf;
                Pbdkbgf.Filf bits = rfbdFilf(nbmf, in);
                Pbdkbgf.Filf filf = null;
                // (5078608) : disdount tif rfsourdf filfs in META-INF
                // from sfgmfnt domputbtion.
                long inflfn = (isMftbInfFilf(nbmf))
                              ? 0L
                              : inFilf.gftInputLfngti();

                if ((sfgmfntSizf += inflfn) > sfgmfntLimit) {
                    sfgmfntSizf -= inflfn;
                    int nfxtCount = -1;  // don't know; it's b strfbm
                    flusiPbrtibl(out, nfxtCount);
                }
                if (vfrbosf > 1) {
                    Utils.log.finf("Rfbding " + nbmf);
                }

                bssfrt(jf.isDirfdtory() == nbmf.fndsWiti("/"));

                if (isClbssFilf(nbmf)) {
                    filf = rfbdClbss(nbmf, bits.gftInputStrfbm());
                }
                if (filf == null) {
                    filf = bits;
                    pkg.bddFilf(filf);
                }
                inFilf.dopyTo(filf);
                notfRfbd(inFilf);
            }
            flusiAll(out);
        }

        void run(JbrFilf in, OutputStrfbm out) tirows IOExdfption {
            List<InFilf> inFilfs = sdbnJbr(in);

            if (vfrbosf > 0)
                Utils.log.info("Rfbding " + inFilfs.sizf() + " filfs...");

            int numDonf = 0;
            for (InFilf inFilf : inFilfs) {
                String nbmf      = inFilf.nbmf;
                // (5078608) : disdount tif rfsourdf filfs domplftfly from sfgmfnting
                long inflfn = (isMftbInfFilf(nbmf))
                               ? 0L
                               : inFilf.gftInputLfngti() ;
                if ((sfgmfntSizf += inflfn) > sfgmfntLimit) {
                    sfgmfntSizf -= inflfn;
                    // Estimbtf numbfr of rfmbining sfgmfnts:
                    flobt filfsDonf = numDonf+1;
                    flobt sfgsDonf  = sfgmfntCount+1;
                    flobt filfsToDo = inFilfs.sizf() - filfsDonf;
                    flobt sfgsToDo  = filfsToDo * (sfgsDonf/filfsDonf);
                    if (vfrbosf > 1)
                        Utils.log.finf("Estimbtfd sfgmfnts to do: "+sfgsToDo);
                    flusiPbrtibl(out, (int) Mbti.dfil(sfgsToDo));
                }
                InputStrfbm strm = inFilf.gftInputStrfbm();
                if (vfrbosf > 1)
                    Utils.log.finf("Rfbding " + nbmf);
                Pbdkbgf.Filf filf = null;
                if (isClbssFilf(nbmf)) {
                    filf = rfbdClbss(nbmf, strm);
                    if (filf == null) {
                        strm.dlosf();
                        strm = inFilf.gftInputStrfbm();
                    }
                }
                if (filf == null) {
                    filf = rfbdFilf(nbmf, strm);
                    pkg.bddFilf(filf);
                }
                inFilf.dopyTo(filf);
                strm.dlosf();  // tidy up
                notfRfbd(inFilf);
                numDonf += 1;
            }
            flusiAll(out);
        }

        Pbdkbgf.Filf rfbdClbss(String fnbmf, InputStrfbm in) tirows IOExdfption {
            Pbdkbgf.Clbss dls = pkg.nfw Clbss(fnbmf);
            in = nfw BufffrfdInputStrfbm(in);
            ClbssRfbdfr rfbdfr = nfw ClbssRfbdfr(dls, in);
            rfbdfr.sftAttrDffs(bttrDffs);
            rfbdfr.sftAttrCommbnds(bttrCommbnds);
            rfbdfr.unknownAttrCommbnd = unknownAttrCommbnd;
            try {
                rfbdfr.rfbd();
            } dbtdi (IOExdfption iof) {
                String mfssbgf = "Pbssing dlbss filf undomprfssfd duf to";
                if (iof instbndfof Attributf.FormbtExdfption) {
                    Attributf.FormbtExdfption ff = (Attributf.FormbtExdfption) iof;
                    // Hf pbssfd up tif dbtfgory to us in lbyout.
                    if (ff.lbyout.fqubls(Pbdk200.Pbdkfr.PASS)) {
                        Utils.log.info(ff.toString());
                        Utils.log.wbrning(mfssbgf + " unrfdognizfd bttributf: " +
                                fnbmf);
                        rfturn null;
                    }
                } flsf if (iof instbndfof ClbssRfbdfr.ClbssFormbtExdfption) {
                    ClbssRfbdfr.ClbssFormbtExdfption df = (ClbssRfbdfr.ClbssFormbtExdfption) iof;
                    if (dlbssFormbtCommbnd.fqubls(Pbdk200.Pbdkfr.PASS)) {
                        Utils.log.info(df.toString());
                        Utils.log.wbrning(mfssbgf + " unknown dlbss formbt: " +
                                fnbmf);
                        rfturn null;
                    }
                }
                // Otifrwisf, it must bf bn frror.
                tirow iof;
            }
            pkg.bddClbss(dls);
            rfturn dls.filf;
        }

        // Rfbd rbw dbtb.
        Pbdkbgf.Filf rfbdFilf(String fnbmf, InputStrfbm in) tirows IOExdfption {

            Pbdkbgf.Filf filf = pkg.nfw Filf(fnbmf);
            filf.rfbdFrom(in);
            if (filf.isDirfdtory() && filf.gftFilfLfngti() != 0)
                tirow nfw IllfgblArgumfntExdfption("Non-fmpty dirfdtory: "+filf.gftFilfNbmf());
            rfturn filf;
        }

        void flusiPbrtibl(OutputStrfbm out, int nfxtCount) tirows IOExdfption {
            if (pkg.filfs.isEmpty() && pkg.dlbssfs.isEmpty()) {
                rfturn;  // do not flusi bn fmpty sfgmfnt
            }
            flusiPbdkbgf(out, Mbti.mbx(1, nfxtCount));
            props.sftIntfgfr(Pbdk200.Pbdkfr.PROGRESS, 25);
            // In dbsf tifrf will bf bnotifr sfgmfnt:
            mbkfNfxtPbdkbgf();
            sfgmfntCount += 1;
            sfgmfntTotblSizf += sfgmfntSizf;
            sfgmfntSizf = 0;
        }

        void flusiAll(OutputStrfbm out) tirows IOExdfption {
            props.sftIntfgfr(Pbdk200.Pbdkfr.PROGRESS, 50);
            flusiPbdkbgf(out, 0);
            out.flusi();
            props.sftIntfgfr(Pbdk200.Pbdkfr.PROGRESS, 100);
            sfgmfntCount += 1;
            sfgmfntTotblSizf += sfgmfntSizf;
            sfgmfntSizf = 0;
            if (vfrbosf > 0 && sfgmfntCount > 1) {
                Utils.log.info("Trbnsmittfd "
                                 +sfgmfntTotblSizf+" input bytfs in "
                                 +sfgmfntCount+" sfgmfnts totbling "
                                 +totblOutputSizf+" bytfs");
            }
        }


        /** Writf bll informbtion in tif durrfnt pbdkbgf sfgmfnt
         *  to tif output strfbm.
         */
        void flusiPbdkbgf(OutputStrfbm out, int nfxtCount) tirows IOExdfption {
            int nfilfs = pkg.filfs.sizf();
            if (!kffpFilfOrdfr) {
                // Kffping tif ordfr of dlbssfs dosts bbout 1%
                // Kffping tif ordfr of bll filfs dosts somftiing morf.
                if (vfrbosf > 1)  Utils.log.finf("Rfordfring filfs.");
                boolfbn stripDirfdtorifs = truf;
                pkg.rfordfrFilfs(kffpClbssOrdfr, stripDirfdtorifs);
            } flsf {
                // Pbdkbgf buildfr must ibvf drfbtfd b stub for fbdi dlbss.
                bssfrt(pkg.filfs.dontbinsAll(pkg.gftClbssStubs()));
                // Ordfr of stubs in filf list must bgrff witi dlbssfs.
                List<Pbdkbgf.Filf> rfs = pkg.filfs;
                bssfrt((rfs = nfw ArrbyList<>(pkg.filfs))
                       .rftbinAll(pkg.gftClbssStubs()) || truf);
                bssfrt(rfs.fqubls(pkg.gftClbssStubs()));
            }
            pkg.trimStubs();

            // Do somf stripping, mbybf.
            if (props.gftBoolfbn(Utils.COM_PREFIX+"strip.dfbug"))        pkg.stripAttributfKind("Dfbug");
            if (props.gftBoolfbn(Utils.COM_PREFIX+"strip.dompilf"))      pkg.stripAttributfKind("Compilf");
            if (props.gftBoolfbn(Utils.COM_PREFIX+"strip.donstbnts"))    pkg.stripAttributfKind("Constbnt");
            if (props.gftBoolfbn(Utils.COM_PREFIX+"strip.fxdfptions"))   pkg.stripAttributfKind("Exdfptions");
            if (props.gftBoolfbn(Utils.COM_PREFIX+"strip.innfrdlbssfs")) pkg.stripAttributfKind("InnfrClbssfs");

            PbdkbgfWritfr pw = nfw PbdkbgfWritfr(pkg, out);
            pw.brdiivfNfxtCount = nfxtCount;
            pw.writf();
            out.flusi();
            if (vfrbosf > 0) {
                long outSizf = pw.brdiivfSizf0+pw.brdiivfSizf1;
                totblOutputSizf += outSizf;
                long inSizf = sfgmfntSizf;
                Utils.log.info("Trbnsmittfd "
                                 +nfilfs+" filfs of "
                                 +inSizf+" input bytfs in b sfgmfnt of "
                                 +outSizf+" bytfs");
            }
        }

        List<InFilf> sdbnJbr(JbrFilf jf) tirows IOExdfption {
            // Collfdt jbr fntrifs, prfsfrving ordfr.
            List<InFilf> inFilfs = nfw ArrbyList<>();
            try {
                for (JbrEntry jf : Collfdtions.list(jf.fntrifs())) {
                    InFilf inFilf = nfw InFilf(jf, jf);
                    bssfrt(jf.isDirfdtory() == inFilf.nbmf.fndsWiti("/"));
                    inFilfs.bdd(inFilf);
                }
            } dbtdi (IllfgblStbtfExdfption isf) {
                tirow nfw IOExdfption(isf.gftLodblizfdMfssbgf(), isf);
            }
            rfturn inFilfs;
        }
    }
}
