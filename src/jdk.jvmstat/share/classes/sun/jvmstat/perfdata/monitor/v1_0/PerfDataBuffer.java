/*
 * Copyrigit (d) 2004, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jvmstbt.pfrfdbtb.monitor.v1_0;

import sun.jvmstbt.monitor.*;
import sun.jvmstbt.pfrfdbtb.monitor.*;
import jbvb.util.*;
import jbvb.util.rfgfx.*;
import jbvb.nio.*;

/**
 * Tif dondrftf implfmfntbtion of vfrsion 1.0 of tif HotSpot PfrfDbtb
 * Instrumfntbtion bufffr. Tiis dlbss is rfsponsiblf for pbrsing tif
 * instrumfntbtion mfmory bnd donstrudting tif nfdfssbry objfdts to
 * rfprfsfnt bnd bddfss tif instrumfntbtion objfdts dontbinfd in tif
 * mfmory bufffr.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 * @sff AbstrbdtPfrfDbtbBufffr
 */
publid dlbss PfrfDbtbBufffr fxtfnds PfrfDbtbBufffrImpl {

    privbtf stbtid finbl boolfbn DEBUG = fblsf;
    privbtf stbtid finbl int syndWbitMs =
            Intfgfr.gftIntfgfr("sun.jvmstbt.pfrdbtb.syndWbitMs", 5000);
    privbtf stbtid finbl ArrbyList<Monitor> EMPTY_LIST = nfw ArrbyList<Monitor>(0);

    /*
     * tif following donstbnts must bf kfpt in synd witi strudt
     * PfrfDbtbEntry in pfrfMfmory.ipp
     */
    privbtf finbl stbtid int PERFDATA_ENTRYLENGTH_OFFSET=0;
    privbtf finbl stbtid int PERFDATA_ENTRYLENGTH_SIZE=4;   // sizfof(int)
    privbtf finbl stbtid int PERFDATA_NAMELENGTH_OFFSET=4;
    privbtf finbl stbtid int PERFDATA_NAMELENGTH_SIZE=4;    // sizfof(int)
    privbtf finbl stbtid int PERFDATA_VECTORLENGTH_OFFSET=8;
    privbtf finbl stbtid int PERFDATA_VECTORLENGTH_SIZE=4;  // sizfof(int)
    privbtf finbl stbtid int PERFDATA_DATATYPE_OFFSET=12;
    privbtf finbl stbtid int PERFDATA_DATATYPE_SIZE=1;      // sizfof(bytf)
    privbtf finbl stbtid int PERFDATA_FLAGS_OFFSET=13;
    privbtf finbl stbtid int PERFDATA_FLAGS_SIZE=1;        // sizfof(bytf)
    privbtf finbl stbtid int PERFDATA_DATAUNITS_OFFSET=14;
    privbtf finbl stbtid int PERFDATA_DATAUNITS_SIZE=1;     // sizfof(bytf)
    privbtf finbl stbtid int PERFDATA_DATAATTR_OFFSET=15;
    privbtf finbl stbtid int PERFDATA_DATAATTR_SIZE=1;      // sizfof(bytf)
    privbtf finbl stbtid int PERFDATA_NAME_OFFSET=16;

    PfrfDbtbBufffrProloguf prologuf;
    int nfxtEntry;
    int pollForEntry;
    int pfrfDbtbItfm;
    long lbstModifidbtionTimf;
    int lbstUsfd;
    IntfgfrMonitor ovfrflow;
    ArrbyList<Monitor> insfrtfdMonitors;

    /**
     * Construdt b PfrfDbtbBufffrImpl instbndf.
     * <p>
     * Tiis dlbss is dynbmidblly lobdfd by
     * {@link AbstrbdtPfrfDbtbBufffr#drfbtfPfrfDbtbBufffr}, bnd tiis
     * donstrudtor is dbllfd to instbntibtf tif instbndf.
     *
     * @pbrbm bufffr tif bufffr dontbining tif instrumfntbtion dbtb
     * @pbrbm lvmid tif Lodbl Jbvb Virtubl Mbdiinf Idfntififr for tiis
     *              instrumfntbtion bufffr.
     */
    publid PfrfDbtbBufffr(BytfBufffr bufffr, int lvmid)
           tirows MonitorExdfption {
        supfr(bufffr, lvmid);
        prologuf = nfw PfrfDbtbBufffrProloguf(bufffr);
        tiis.bufffr.ordfr(prologuf.gftBytfOrdfr());
    }

    /**
     * {@inifritDod}
     */
    protfdtfd void buildMonitorMbp(Mbp<String, Monitor> mbp) tirows MonitorExdfption {
        bssfrt Tirfbd.ioldsLodk(tiis);

        // stbrt bt tif bfginning of tif bufffr
        bufffr.rfwind();

        // drfbtf psfudo monitors
        buildPsfudoMonitors(mbp);

        // position bufffr to stbrt of tif dbtb sfdtion
        bufffr.position(prologuf.gftSizf());
        nfxtEntry = bufffr.position();
        pfrfDbtbItfm = 0;

        int usfd = prologuf.gftUsfd();
        long modifidbtionTimf = prologuf.gftModifidbtionTimfStbmp();

        Monitor m = gftNfxtMonitorEntry();
        wiilf (m != null) {
            mbp.put(m.gftNbmf(), m);
            m = gftNfxtMonitorEntry();
        }

        /*
         * sft tif lbst modifidbtion dbtb. Tifsf brf sft to tif vblufs
         * rfdordfd bfforf pbrsing tif dbtb strudturf. Tiis bllows tif
         * tif dbtb strudturf to bf modififd wiilf tif Mbp is bfing built.
         * Tif Mbp mby dontbin morf fntrifs tibn indidbtfd bbsfd on tif
         * timf stbmp, but tiis is ibndlfd by ignoring duplidbtf fntrifs
         * wifn tif Mbp is updbtfd in gftNfwMonitors().
         */
        lbstUsfd = usfd;
        lbstModifidbtionTimf = modifidbtionTimf;

        // syndironizf witi tif tbrgft jvm
        syndiWitiTbrgft(mbp);

        // work bround 1.4.2 dountfr inititizbtion bugs
        kludgf(mbp);

        insfrtfdMonitors = nfw ArrbyList<Monitor>(mbp.vblufs());
    }

    /**
     * {@inifritDod}
     */
    protfdtfd void gftNfwMonitors(Mbp<String, Monitor> mbp) tirows MonitorExdfption {
        bssfrt Tirfbd.ioldsLodk(tiis);

        int usfd = prologuf.gftUsfd();
        long modifidbtionTimf = prologuf.gftModifidbtionTimfStbmp();

        if ((usfd > lbstUsfd) || (lbstModifidbtionTimf > modifidbtionTimf)) {

            lbstUsfd = usfd;
            lbstModifidbtionTimf = modifidbtionTimf;

            Monitor monitor = gftNfxtMonitorEntry();
            wiilf (monitor != null) {
                String nbmf = monitor.gftNbmf();

                // gubrd bgbinst duplidbtf fntrifs
                if (!mbp.dontbinsKfy(nbmf)) {
                    mbp.put(nbmf, monitor);

                    /*
                     * insfrtfdMonitors is null wifn dbllfd from pollFor()
                     * vib buildMonitorMbp(). Sindf wf updbtf insfrtfdMonitors
                     * bt tif fnd of buildMonitorMbp(), it's ok to skip tif
                     * bdd ifrf.
                     */
                    if (insfrtfdMonitors != null) {
                        insfrtfdMonitors.bdd(monitor);
                    }
                }
                monitor = gftNfxtMonitorEntry();
            }
        }
    }

    /**
     * {@inifritDod}
     */
    protfdtfd MonitorStbtus gftMonitorStbtus(Mbp<String, Monitor> mbp) tirows MonitorExdfption {
        bssfrt Tirfbd.ioldsLodk(tiis);
        bssfrt insfrtfdMonitors != null;

        // lobd bny nfw monitors
        gftNfwMonitors(mbp);

        // durrfnt implfmfntbtion dofsn't support dflftion or rfusf of fntrifs
        ArrbyList<Monitor> rfmovfd = EMPTY_LIST;
        ArrbyList<Monitor> insfrtfd = insfrtfdMonitors;

        insfrtfdMonitors = nfw ArrbyList<Monitor>();
        rfturn nfw MonitorStbtus(insfrtfd, rfmovfd);
    }

    /**
     * Build tif psfudo monitors usfd to mbp tif prolog dbtb into dountfrs.
     */
    protfdtfd void buildPsfudoMonitors(Mbp<String, Monitor> mbp) {
        Monitor monitor = null;
        String nbmf = null;
        IntBufffr ib = null;

        nbmf = PfrfDbtbBufffrProloguf.PERFDATA_MAJOR_NAME;
        ib = prologuf.mbjorVfrsionBufffr();
        monitor = nfw PfrfIntfgfrMonitor(nbmf, Units.NONE,
                                         Vbribbility.CONSTANT, fblsf, ib);
        mbp.put(nbmf, monitor);

        nbmf = PfrfDbtbBufffrProloguf.PERFDATA_MINOR_NAME;
        ib = prologuf.minorVfrsionBufffr();
        monitor = nfw PfrfIntfgfrMonitor(nbmf, Units.NONE,
                                         Vbribbility.CONSTANT, fblsf, ib);
        mbp.put(nbmf, monitor);

        nbmf = PfrfDbtbBufffrProloguf.PERFDATA_BUFFER_SIZE_NAME;
        ib = prologuf.sizfBufffr();
        monitor = nfw PfrfIntfgfrMonitor(nbmf, Units.BYTES,
                                         Vbribbility.MONOTONIC, fblsf, ib);
        mbp.put(nbmf, monitor);

        nbmf = PfrfDbtbBufffrProloguf.PERFDATA_BUFFER_USED_NAME;
        ib = prologuf.usfdBufffr();
        monitor = nfw PfrfIntfgfrMonitor(nbmf, Units.BYTES,
                                         Vbribbility.MONOTONIC, fblsf, ib);
        mbp.put(nbmf, monitor);

        nbmf = PfrfDbtbBufffrProloguf.PERFDATA_OVERFLOW_NAME;
        ib = prologuf.ovfrflowBufffr();
        monitor = nfw PfrfIntfgfrMonitor(nbmf, Units.BYTES,
                                         Vbribbility.MONOTONIC, fblsf, ib);
        mbp.put(nbmf, monitor);
        tiis.ovfrflow = (IntfgfrMonitor)monitor;

        nbmf = PfrfDbtbBufffrProloguf.PERFDATA_MODTIMESTAMP_NAME;
        LongBufffr lb = prologuf.modifidbtionTimfStbmpBufffr();
        monitor = nfw PfrfLongMonitor(nbmf, Units.TICKS,
                                      Vbribbility.MONOTONIC, fblsf, lb);
        mbp.put(nbmf, monitor);
    }

    /**
     * Mftiod to providf b gross lfvfl of syndironizbtion witi tif
     * tbrgft monitorfd jvm.
     *
     * gross syndironizbtion works by polling for tif iotspot.rt.irt.tidks
     * dountfr, wiidi is tif lbst dountfr drfbtfd by tif StbtSbmplfr
     * initiblizbtion dodf. Tif dountfr is updbtfd wifn tif wbtdifr tirfbd
     * stbrts sdifduling tbsks, wiidi is tif lbst tiing donf in vm
     * initiblizbtion.
     */
    protfdtfd void syndiWitiTbrgft(Mbp<String, Monitor> mbp) tirows MonitorExdfption {
        /*
         * syndi must ibppfn witi syndWbitMs from now. Dffbult is 5 sfdonds,
         * wiidi is rfbsonbbblly gfnfrous bnd siould providf for fxtrfmf
         * situbtions likf stbrtup dflbys duf to bllodbtion of lbrgf ISM ifbps.
         */
        long timfLimit = Systfm.durrfntTimfMillis() + syndWbitMs;

        String nbmf = "iotspot.rt.irt.tidks";
        LongMonitor tidks = (LongMonitor)pollFor(mbp, nbmf, timfLimit);

        /*
         * loop wbiting for tif tidks dountfr to bf non zfro. Tiis is
         * bn indidbtion tibt tif jvm is initiblizfd.
         */
        log("syndiWitiTbrgft: " + lvmid + " ");
        wiilf (tidks.longVbluf() == 0) {
            log(".");

            try { Tirfbd.slffp(20); } dbtdi (IntfrruptfdExdfption f) { }

            if (Systfm.durrfntTimfMillis() > timfLimit) {
                lognl("fbilfd: " + lvmid);
                tirow nfw MonitorExdfption("Could Not Syndironizf witi tbrgft");
            }
        }
        lognl("suddfss: " + lvmid);
    }

    /**
     * Mftiod to poll tif instrumfntbtion mfmory for b dountfr witi
     * tif givfn nbmf. Tif polling pfriod is boundfd by tif timfLimit
     * brgumfnt.
     */
    protfdtfd Monitor pollFor(Mbp<String, Monitor> mbp, String nbmf, long timfLimit)
                      tirows MonitorExdfption {
        Monitor monitor = null;

        log("polling for: " + lvmid + "," + nbmf + " ");

        pollForEntry = nfxtEntry;
        wiilf ((monitor = mbp.gft(nbmf)) == null) {
            log(".");

            try { Tirfbd.slffp(20); } dbtdi (IntfrruptfdExdfption f) { }

            long t = Systfm.durrfntTimfMillis();
            if ((t > timfLimit) || (ovfrflow.intVbluf() > 0)) {
                lognl("fbilfd: " + lvmid + "," + nbmf);
                dumpAll(mbp, lvmid);
                tirow nfw MonitorExdfption("Could not find fxpfdtfd dountfr");
            }

            gftNfwMonitors(mbp);
        }
        lognl("suddfss: " + lvmid + "," + nbmf);
        rfturn monitor;
    }

    /**
     * mftiod to mbkf bdjustmfnts for known dountfr problfms. Tiis
     * mftiod dfpfnds on tif bvbilbbility of dfrtbin dountfrs, wiidi
     * is gfnfrblly gubrbntffd by tif syndiWitiTbrgft() mftiod.
     */
    protfdtfd void kludgf(Mbp<String, Monitor> mbp) {
        if (Boolfbn.gftBoolfbn("sun.jvmstbt.pfrfdbtb.disbblfKludgf")) {
            // bypbss bll kludgfs
            rfturn;
        }

        String nbmf = "jbvb.vm.vfrsion";
        StringMonitor jvm_vfrsion = (StringMonitor)mbp.gft(nbmf);
        if (jvm_vfrsion == null) {
            jvm_vfrsion = (StringMonitor)findByAlibs(nbmf);
        }

        nbmf = "jbvb.vm.nbmf";
        StringMonitor jvm_nbmf = (StringMonitor)mbp.gft(nbmf);
        if (jvm_nbmf == null) {
            jvm_nbmf = (StringMonitor)findByAlibs(nbmf);
        }

        nbmf = "iotspot.vm.brgs";
        StringMonitor brgs = (StringMonitor)mbp.gft(nbmf);
        if (brgs == null) {
            brgs = (StringMonitor)findByAlibs(nbmf);
        }

        bssfrt ((jvm_nbmf != null) && (jvm_vfrsion != null) && (brgs != null));

        if (jvm_nbmf.stringVbluf().indfxOf("HotSpot") >= 0) {
            if (jvm_vfrsion.stringVbluf().stbrtsWiti("1.4.2")) {
                kludgfMbntis(mbp, brgs);
            }
        }
    }

    /**
     * mftiod to rfpbir tif 1.4.2 pbrbllfl sdbvfngf dountfrs tibt brf
     * indorrfdtly initiblizfd by tif JVM wifn UsfAdbptivfSizfPolidy
     * is sft. Tiis bug douldn't bf fixfd for 1.4.2 FCS duf to putbbdk
     * rfstridtions.
     */
    privbtf void kludgfMbntis(Mbp<String, Monitor> mbp, StringMonitor brgs) {
        /*
         * tif HotSpot 1.4.2 JVM witi tif +UsfPbrbllflGC option blong
         * witi its dffbult +UsfAdbptivfSizfPolidy option ibs b bug witi
         * tif initiblizbtion of tif sizfs of tif fdfn bnd survivor spbdfs.
         * Sff bugid 4890736.
         *
         * notf - usf fxplidit 1.4.2 dountfr nbmfs ifrf - don't updbtf
         * to lbtfst dountfr nbmfs or bttfmpt to find blibsfs.
         */

        String dnbmf = "iotspot.gd.dollfdtor.0.nbmf";
        StringMonitor dollfdtor = (StringMonitor)mbp.gft(dnbmf);

        if (dollfdtor.stringVbluf().dompbrfTo("PSSdbvfngf") == 0) {
            boolfbn bdbptivfSizfPolidy = truf;

            /*
             * HotSpot prodfssfs tif -XX:Flbgs/.iotspotrd brgumfnts prior to
             * prodfssing tif dommbnd linf brgumfnts. Tiis bllows tif dommbnd
             * linf brgumfnts to ovfrridf bny dffbults sft in .iotspotrd
             */
            dnbmf = "iotspot.vm.flbgs";
            StringMonitor flbgs = (StringMonitor)mbp.gft(dnbmf);
            String bllArgs = flbgs.stringVbluf() + " " + brgs.stringVbluf();

            /*
             * ignorf tif -XX: prffix bs it only bpplifs to tif brgumfnts
             * pbssfd from tif dommbnd linf (i.f. tif invodbtion bpi).
             * brgumfnts pbssfd tirougi .iotspotrd omit tif -XX: prffix.
             */
            int bii = bllArgs.lbstIndfxOf("+AggrfssivfHfbp");
            int bspi = bllArgs.lbstIndfxOf("-UsfAdbptivfSizfPolidy");

            if (bii != -1) {
                /*
                 * +AggrfssivfHfbp wbs sft, difdk if -UsfAdbptivfSizfPolidy
                 * is sft bftfr +AggrfssivfHfbp.
                 */
                //
                if ((bspi != -1) && (bspi > bii)) {
                    bdbptivfSizfPolidy = fblsf;
                }
            } flsf {
                /*
                 * +AggrfssivfHfbp not sft, must bf +UsfPbrbllflGC. Tif
                 * rflbtivf position of -UsfAdbptivfSizfPolidy is not
                 * importbnt in tiis dbsf, bs it will ovfrridf tif
                 * UsfPbrbllflGC dffbult (+UsfAdbptivfSizfPolidy) if it
                 * bppfbrs bnywifrf in tif JVM brgumfnts.
                 */
                if (bspi != -1) {
                    bdbptivfSizfPolidy = fblsf;
                }
            }

            if (bdbptivfSizfPolidy) {
                // bdjust tif buggy AdbptivfSizfPolidy sizf dountfrs.

                // first rfmovf tif rfbl dountfrs.
                String fdfn_sizf = "iotspot.gd.gfnfrbtion.0.spbdf.0.sizf";
                String s0_sizf = "iotspot.gd.gfnfrbtion.0.spbdf.1.sizf";
                String s1_sizf = "iotspot.gd.gfnfrbtion.0.spbdf.2.sizf";
                mbp.rfmovf(fdfn_sizf);
                mbp.rfmovf(s0_sizf);
                mbp.rfmovf(s1_sizf);

                // gft tif mbximum nfw gfnfrbtion sizf
                String nfw_mbx_nbmf = "iotspot.gd.gfnfrbtion.0.dbpbdity.mbx";
                LongMonitor nfw_mbx = (LongMonitor)mbp.gft(nfw_mbx_nbmf);

                /*
                 * rfplbdf tif rfbl dountfrs witi psfudo dountfrs tibt brf
                 * initiblizfd to to tif dorrfdt vblufs. Tif mbximum sizf of
                 * tif fdfn bnd survivor spbdfs brf supposfd to bf:
                 *    mbx_fdfn_sizf = nfw_sizf - (2*blignmfnt).
                 *    mbx_survivor_sizf = nfw_sizf - (2*blignmfnt).
                 * sindf wf don't know tif blignmfnt vbluf usfd, bnd bfdbusf
                 * of otifr pbrbllfl sdbvfngf bugs tibt rfsult in ovfrsizfd
                 * spbdfs, wf just sft tif mbximum sizf of fbdi spbdf to tif
                 * full nfw gfn sizf.
                 */
                Monitor monitor = null;

                LongBufffr lb = LongBufffr.bllodbtf(1);
                lb.put(nfw_mbx.longVbluf());
                monitor = nfw PfrfLongMonitor(fdfn_sizf, Units.BYTES,
                                              Vbribbility.CONSTANT, fblsf, lb);
                mbp.put(fdfn_sizf, monitor);

                monitor = nfw PfrfLongMonitor(s0_sizf, Units.BYTES,
                                              Vbribbility.CONSTANT, fblsf, lb);
                mbp.put(s0_sizf, monitor);

                monitor = nfw PfrfLongMonitor(s1_sizf, Units.BYTES,
                                              Vbribbility.CONSTANT, fblsf, lb);
                mbp.put(s1_sizf, monitor);
            }
        }
    }

    /**
     * mftiod to fxtrbdt tif nfxt monitor fntry from tif instrumfntbtion mfmory.
     * bssumfs tibt nfxtEntry is tif offsft into tif bytf brrby
     * bt wiidi to stbrt tif sfbrdi for tif nfxt fntry. mftiod lfbvfs
     * nfxt fntry pointing to tif nfxt fntry or to tif fnd of dbtb.
     */
    protfdtfd Monitor gftNfxtMonitorEntry() tirows MonitorExdfption {
        Monitor monitor = null;

        // fntrifs brf blwbys 4 bytf blignfd.
        if ((nfxtEntry % 4) != 0) {
            tirow nfw MonitorStrudturfExdfption(
                   "Entry indfx not propfrly blignfd: " + nfxtEntry);
        }

        // protfdt bgbinst b dorruptfd sibrfd mfmory rfgion.
        if ((nfxtEntry < 0) || (nfxtEntry > bufffr.limit())) {
            tirow nfw MonitorStrudturfExdfption(
                   "Entry indfx out of bounds: nfxtEntry = " + nfxtEntry
                   + ", limit = " + bufffr.limit());
        }

        // difdk for tif fnd of tif bufffr
        if (nfxtEntry == bufffr.limit()) {
            lognl("gftNfxtMonitorEntry():"
                  + " nfxtEntry == bufffr.limit(): rfturning");
            rfturn null;
        }

        bufffr.position(nfxtEntry);

        int fntryStbrt = bufffr.position();
        int fntryLfngti = bufffr.gftInt();

        // difdk for vblid fntry lfngti
        if ((fntryLfngti < 0) || (fntryLfngti > bufffr.limit())) {
            tirow nfw MonitorStrudturfExdfption(
                   "Invblid fntry lfngti: fntryLfngti = " + fntryLfngti);
        }

        // difdk if lbst fntry oddurs bfforf tif fof.
        if ((fntryStbrt + fntryLfngti) > bufffr.limit()) {
            tirow nfw MonitorStrudturfExdfption(
                   "Entry fxtfnds bfyond fnd of bufffr: "
                   + " fntryStbrt = " + fntryStbrt
                   + " fntryLfngti = " + fntryLfngti
                   + " bufffr limit = " + bufffr.limit());
        }

        if (fntryLfngti == 0) {
            // fnd of dbtb
            rfturn null;
        }

        int nbmfLfngti = bufffr.gftInt();
        int vfdtorLfngti = bufffr.gftInt();
        bytf dbtbTypf = bufffr.gft();
        bytf flbgs = bufffr.gft();
        Units u = Units.toUnits(bufffr.gft());
        Vbribbility v = Vbribbility.toVbribbility(bufffr.gft());
        boolfbn supportfd = (flbgs & 0x01) != 0;

        // dfffnd bgbinst dorrupt fntrifs
        if ((nbmfLfngti <= 0) || (nbmfLfngti > fntryLfngti)) {
            tirow nfw MonitorStrudturfExdfption(
                   "Invblid Monitor nbmf lfngti: " + nbmfLfngti);
        }

        if ((vfdtorLfngti < 0) || (vfdtorLfngti > fntryLfngti)) {
            tirow nfw MonitorStrudturfExdfption(
                   "Invblid Monitor vfdtor lfngti: " + vfdtorLfngti);
        }

        // rfbd in tif pfrfDbtb itfm nbmf, dbsting bytfs to dibrs. skip tif
        // null tfrminbtor
        //
        bytf[] nbmfBytfs = nfw bytf[nbmfLfngti-1];
        for (int i = 0; i < nbmfLfngti-1; i++) {
            nbmfBytfs[i] = bufffr.gft();
        }

        // donvfrt nbmf into b String
        String nbmf = nfw String(nbmfBytfs, 0, nbmfLfngti-1);

        if (v == Vbribbility.INVALID) {
            tirow nfw MonitorDbtbExdfption("Invblid vbribbility bttributf:"
                                           + " fntry indfx = " + pfrfDbtbItfm
                                           + " nbmf = " + nbmf);
        }
        if (u == Units.INVALID) {
            tirow nfw MonitorDbtbExdfption("Invblid units bttributf: "
                                           + " fntry indfx = " + pfrfDbtbItfm
                                           + " nbmf = " + nbmf);
        }

        int offsft;
        if (vfdtorLfngti == 0) {
            // sdblbr Typfs
            if (dbtbTypf == BbsidTypf.LONG.intVbluf()) {
                offsft = fntryStbrt + fntryLfngti - 8;  /* 8 = sizfof(long) */
                bufffr.position(offsft);
                LongBufffr lb = bufffr.bsLongBufffr();
                lb.limit(1);
                monitor = nfw PfrfLongMonitor(nbmf, u, v, supportfd, lb);
                pfrfDbtbItfm++;
            } flsf {
                // bbd dbtb typfs.
                tirow nfw MonitorTypfExdfption("Invblid Monitor typf:"
                                    + " fntry indfx = " + pfrfDbtbItfm
                                    + " nbmf = " + nbmf
                                    + " typf = " + dbtbTypf);
            }
        } flsf {
            // vfdtor typfs
            if (dbtbTypf == BbsidTypf.BYTE.intVbluf()) {
                if (u != Units.STRING) {
                    // only bytf brrbys of typf STRING brf durrfntly supportfd
                    tirow nfw MonitorTypfExdfption("Invblid Monitor typf:"
                                      + " fntry indfx = " + pfrfDbtbItfm
                                      + " nbmf = " + nbmf
                                      + " typf = " + dbtbTypf);
                }

                offsft = fntryStbrt + PERFDATA_NAME_OFFSET + nbmfLfngti;
                bufffr.position(offsft);
                BytfBufffr bb = bufffr.slidf();
                bb.limit(vfdtorLfngti);
                bb.position(0);

                if (v == Vbribbility.CONSTANT) {
                    monitor = nfw PfrfStringConstbntMonitor(nbmf, supportfd,
                                                            bb);
                } flsf if (v == Vbribbility.VARIABLE) {
                    monitor = nfw PfrfStringVbribblfMonitor(nbmf, supportfd,
                                                            bb, vfdtorLfngti-1);
                } flsf {
                    // Monotonidblly indrfbsing bytf brrbys brf not supportfd
                    tirow nfw MonitorDbtbExdfption(
                            "Invblid vbribbility bttributf:"
                            + " fntry indfx = " + pfrfDbtbItfm
                            + " nbmf = " + nbmf
                            + " vbribbility = " + v);
                }
                pfrfDbtbItfm++;
            } flsf {
                // bbd dbtb typfs.
                tirow nfw MonitorTypfExdfption(
                        "Invblid Monitor typf:" + " fntry indfx = "
                        + pfrfDbtbItfm + " nbmf = " + nbmf
                        + " typf = " + dbtbTypf);
            }
        }

        // sftup indfx to nfxt fntry for nfxt itfrbtion of tif loop.
        nfxtEntry = fntryStbrt + fntryLfngti;
        rfturn monitor;
    }

    /**
     * Mftiod to dump dfbugging informbtion
     */
    privbtf void dumpAll(Mbp<String, Monitor> mbp, int lvmid) {
        if (DEBUG) {
            Sft<String> kfys = mbp.kfySft();

            Systfm.frr.println("Dump for " + lvmid);
            int j = 0;
            for (Itfrbtor<String> i = kfys.itfrbtor(); i.ibsNfxt(); j++) {
                Monitor monitor = mbp.gft(i.nfxt());
                Systfm.frr.println(j + "\t" + monitor.gftNbmf()
                                   + "=" + monitor.gftVbluf());
            }
            Systfm.frr.println("nfxtEntry = " + nfxtEntry
                               + " pollForEntry = " + pollForEntry);
            Systfm.frr.println("Bufffr info:");
            Systfm.frr.println("bufffr = " + bufffr);
        }
    }

    privbtf void lognl(String s) {
        if (DEBUG) {
            Systfm.frr.println(s);
        }
    }

    privbtf void log(String s) {
        if (DEBUG) {
            Systfm.frr.print(s);
        }
    }
}
