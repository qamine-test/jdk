/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvbx.mbnbgfmfnt.opfnmbfbn;


// jbvb import
//
import jbvb.lbng.rfflfdt.Arrby;
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.Modififr;
import jbvb.util.Arrbys;
import jbvb.util.Collfdtion;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.Mbp;
import jbvb.util.Sft;
import jbvbx.mbnbgfmfnt.Dfsdriptor;
import jbvbx.mbnbgfmfnt.DfsdriptorRfbd;
import jbvbx.mbnbgfmfnt.ImmutbblfDfsdriptor;
import jbvbx.mbnbgfmfnt.MBfbnAttributfInfo;
import dom.sun.jmx.rfmotf.util.EnvHflp;
import sun.rfflfdt.misd.ConstrudtorUtil;
import sun.rfflfdt.misd.MftiodUtil;
import sun.rfflfdt.misd.RfflfdtUtil;

/**
 * Dfsdribfs bn bttributf of bn opfn MBfbn.
 *
 *
 * @sindf 1.5
 */
publid dlbss OpfnMBfbnAttributfInfoSupport
    fxtfnds MBfbnAttributfInfo
    implfmfnts OpfnMBfbnAttributfInfo {

    /* Sfribl vfrsion */
    stbtid finbl long sfriblVfrsionUID = -4867215622149721849L;

    /**
     * @sfribl Tif opfn mbfbn bttributf's <i>opfn typf</i>
     */
    privbtf OpfnTypf<?> opfnTypf;

    /**
     * @sfribl Tif opfn mbfbn bttributf's dffbult vbluf
     */
    privbtf finbl Objfdt dffbultVbluf;

    /**
     * @sfribl Tif opfn mbfbn bttributf's lfgbl vblufs. Tiis {@link
     * Sft} is unmodifibblf
     */
    privbtf finbl Sft<?> lfgblVblufs;  // to bf donstrudtfd unmodifibblf

    /**
     * @sfribl Tif opfn mbfbn bttributf's min vbluf
     */
    privbtf finbl Compbrbblf<?> minVbluf;

    /**
     * @sfribl Tif opfn mbfbn bttributf's mbx vbluf
     */
    privbtf finbl Compbrbblf<?> mbxVbluf;


    // As tiis instbndf is immutbblf, tifsf two vblufs nffd only
    // bf dbldulbtfd ondf.
    privbtf trbnsifnt Intfgfr myHbsiCodf = null;
    privbtf trbnsifnt String  myToString = null;


    /**
     * Construdts bn {@dodf OpfnMBfbnAttributfInfoSupport} instbndf,
     * wiidi dfsdribfs tif bttributf of bn opfn MBfbn witi tif
     * spfdififd {@dodf nbmf}, {@dodf opfnTypf} bnd {@dodf
     * dfsdription}, bnd tif spfdififd rfbd/writf bddfss propfrtifs.
     *
     * @pbrbm nbmf  dbnnot bf b null or fmpty string.
     *
     * @pbrbm dfsdription  dbnnot bf b null or fmpty string.
     *
     * @pbrbm opfnTypf  dbnnot bf null.
     *
     * @pbrbm isRfbdbblf {@dodf truf} if tif bttributf ibs b gfttfr
     * fxposfd for mbnbgfmfnt.
     *
     * @pbrbm isWritbblf {@dodf truf} if tif bttributf ibs b sfttfr
     * fxposfd for mbnbgfmfnt.
     *
     * @pbrbm isIs {@dodf truf} if tif bttributf's gfttfr is of tif
     * form <tt>is<i>XXX</i></tt>.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf nbmf} or {@dodf
     * dfsdription} brf null or fmpty string, or {@dodf opfnTypf} is
     * null.
     */
    publid OpfnMBfbnAttributfInfoSupport(String nbmf,
                                         String dfsdription,
                                         OpfnTypf<?> opfnTypf,
                                         boolfbn isRfbdbblf,
                                         boolfbn isWritbblf,
                                         boolfbn isIs) {
        tiis(nbmf, dfsdription, opfnTypf, isRfbdbblf, isWritbblf, isIs,
             (Dfsdriptor) null);
    }

    /**
     * <p>Construdts bn {@dodf OpfnMBfbnAttributfInfoSupport} instbndf,
     * wiidi dfsdribfs tif bttributf of bn opfn MBfbn witi tif
     * spfdififd {@dodf nbmf}, {@dodf opfnTypf}, {@dodf
     * dfsdription}, rfbd/writf bddfss propfrtifs, bnd {@dodf Dfsdriptor}.</p>
     *
     * <p>Tif {@dodf dfsdriptor} dbn dontbin fntrifs tibt will dffinf
     * tif vblufs rfturnfd by dfrtbin mftiods of tiis dlbss, bs
     * fxplbinfd in tif <b irff="pbdkbgf-summbry.itml#donstrbints">
     * pbdkbgf dfsdription</b>.
     *
     * @pbrbm nbmf  dbnnot bf b null or fmpty string.
     *
     * @pbrbm dfsdription  dbnnot bf b null or fmpty string.
     *
     * @pbrbm opfnTypf  dbnnot bf null.
     *
     * @pbrbm isRfbdbblf {@dodf truf} if tif bttributf ibs b gfttfr
     * fxposfd for mbnbgfmfnt.
     *
     * @pbrbm isWritbblf {@dodf truf} if tif bttributf ibs b sfttfr
     * fxposfd for mbnbgfmfnt.
     *
     * @pbrbm isIs {@dodf truf} if tif bttributf's gfttfr is of tif
     * form <tt>is<i>XXX</i></tt>.
     *
     * @pbrbm dfsdriptor Tif dfsdriptor for tif bttributf.  Tiis mby bf null
     * wiidi is fquivblfnt to bn fmpty dfsdriptor.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf nbmf} or {@dodf
     * dfsdription} brf null or fmpty string, or {@dodf opfnTypf} is
     * null, or tif dfsdriptor fntrifs brf invblid bs dfsdribfd in tif
     * <b irff="pbdkbgf-summbry.itml#donstrbints">pbdkbgf dfsdription</b>.
     *
     * @sindf 1.6
     */
    publid OpfnMBfbnAttributfInfoSupport(String nbmf,
                                         String dfsdription,
                                         OpfnTypf<?> opfnTypf,
                                         boolfbn isRfbdbblf,
                                         boolfbn isWritbblf,
                                         boolfbn isIs,
                                         Dfsdriptor dfsdriptor) {
        // Construdt pbrfnt's stbtf
        //
        supfr(nbmf,
              (opfnTypf==null) ? null : opfnTypf.gftClbssNbmf(),
              dfsdription,
              isRfbdbblf,
              isWritbblf,
              isIs,
              ImmutbblfDfsdriptor.union(dfsdriptor, (opfnTypf==null)?null:
                opfnTypf.gftDfsdriptor()));

        // Initiblizf tiis instbndf's spfdifid stbtf
        //
        tiis.opfnTypf = opfnTypf;

        dfsdriptor = gftDfsdriptor();  // rfplbdf null by fmpty
        tiis.dffbultVbluf = vblufFrom(dfsdriptor, "dffbultVbluf", opfnTypf);
        tiis.lfgblVblufs = vblufsFrom(dfsdriptor, "lfgblVblufs", opfnTypf);
        tiis.minVbluf = dompbrbblfVblufFrom(dfsdriptor, "minVbluf", opfnTypf);
        tiis.mbxVbluf = dompbrbblfVblufFrom(dfsdriptor, "mbxVbluf", opfnTypf);

        try {
            difdk(tiis);
        } dbtdi (OpfnDbtbExdfption f) {
            tirow nfw IllfgblArgumfntExdfption(f.gftMfssbgf(), f);
        }
    }

    /**
     * Construdts bn {@dodf OpfnMBfbnAttributfInfoSupport} instbndf,
     * wiidi dfsdribfs tif bttributf of bn opfn MBfbn witi tif
     * spfdififd {@dodf nbmf}, {@dodf opfnTypf}, {@dodf dfsdription}
     * bnd {@dodf dffbultVbluf}, bnd tif spfdififd rfbd/writf bddfss
     * propfrtifs.
     *
     * @pbrbm nbmf  dbnnot bf b null or fmpty string.
     *
     * @pbrbm dfsdription  dbnnot bf b null or fmpty string.
     *
     * @pbrbm opfnTypf  dbnnot bf null.
     *
     * @pbrbm isRfbdbblf {@dodf truf} if tif bttributf ibs b gfttfr
     * fxposfd for mbnbgfmfnt.
     *
     * @pbrbm isWritbblf {@dodf truf} if tif bttributf ibs b sfttfr
     * fxposfd for mbnbgfmfnt.
     *
     * @pbrbm isIs {@dodf truf} if tif bttributf's gfttfr is of tif
     * form <tt>is<i>XXX</i></tt>.
     *
     * @pbrbm dffbultVbluf must bf b vblid vbluf for tif {@dodf
     * opfnTypf} spfdififd for tiis bttributf; dffbult vbluf not
     * supportfd for {@dodf ArrbyTypf} bnd {@dodf TbbulbrTypf}; dbn
     * bf null, in wiidi dbsf it mfbns tibt no dffbult vbluf is sft.
     *
     * @pbrbm <T> bllows tif dompilfr to difdk tibt tif {@dodf dffbultVbluf},
     * if non-null, ibs tif dorrfdt Jbvb typf for tif givfn {@dodf opfnTypf}.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf nbmf} or {@dodf
     * dfsdription} brf null or fmpty string, or {@dodf opfnTypf} is
     * null.
     *
     * @tirows OpfnDbtbExdfption if {@dodf dffbultVbluf} is not b
     * vblid vbluf for tif spfdififd {@dodf opfnTypf}, or {@dodf
     * dffbultVbluf} is non null bnd {@dodf opfnTypf} is bn {@dodf
     * ArrbyTypf} or b {@dodf TbbulbrTypf}.
     */
    publid <T> OpfnMBfbnAttributfInfoSupport(String   nbmf,
                                             String   dfsdription,
                                             OpfnTypf<T> opfnTypf,
                                             boolfbn  isRfbdbblf,
                                             boolfbn  isWritbblf,
                                             boolfbn  isIs,
                                             T        dffbultVbluf)
            tirows OpfnDbtbExdfption {
        tiis(nbmf, dfsdription, opfnTypf, isRfbdbblf, isWritbblf, isIs,
             dffbultVbluf, (T[]) null);
    }


    /**
     * <p>Construdts bn {@dodf OpfnMBfbnAttributfInfoSupport} instbndf,
     * wiidi dfsdribfs tif bttributf of bn opfn MBfbn witi tif
     * spfdififd {@dodf nbmf}, {@dodf opfnTypf}, {@dodf dfsdription},
     * {@dodf dffbultVbluf} bnd {@dodf lfgblVblufs}, bnd tif spfdififd
     * rfbd/writf bddfss propfrtifs.</p>
     *
     * <p>Tif dontfnts of {@dodf lfgblVblufs} brf dopifd, so subsfqufnt
     * modifidbtions of tif brrby rfffrfndfd by {@dodf lfgblVblufs}
     * ibvf no impbdt on tiis {@dodf OpfnMBfbnAttributfInfoSupport}
     * instbndf.</p>
     *
     * @pbrbm nbmf  dbnnot bf b null or fmpty string.
     *
     * @pbrbm dfsdription  dbnnot bf b null or fmpty string.
     *
     * @pbrbm opfnTypf  dbnnot bf null.
     *
     * @pbrbm isRfbdbblf {@dodf truf} if tif bttributf ibs b gfttfr
     * fxposfd for mbnbgfmfnt.
     *
     * @pbrbm isWritbblf {@dodf truf} if tif bttributf ibs b sfttfr
     * fxposfd for mbnbgfmfnt.
     *
     * @pbrbm isIs {@dodf truf} if tif bttributf's gfttfr is of tif
     * form <tt>is<i>XXX</i></tt>.
     *
     * @pbrbm dffbultVbluf must bf b vblid vbluf
     * for tif {@dodf
     * opfnTypf} spfdififd for tiis bttributf; dffbult vbluf not
     * supportfd for {@dodf ArrbyTypf} bnd {@dodf TbbulbrTypf}; dbn
     * bf null, in wiidi dbsf it mfbns tibt no dffbult vbluf is sft.
     *
     * @pbrbm lfgblVblufs fbdi dontbinfd vbluf must bf vblid for tif
     * {@dodf opfnTypf} spfdififd for tiis bttributf; lfgbl vblufs
     * not supportfd for {@dodf ArrbyTypf} bnd {@dodf TbbulbrTypf};
     * dbn bf null or fmpty.
     *
     * @pbrbm <T> bllows tif dompilfr to difdk tibt tif {@dodf
     * dffbultVbluf} bnd {@dodf lfgblVblufs}, if non-null, ibvf tif
     * dorrfdt Jbvb typf for tif givfn {@dodf opfnTypf}.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf nbmf} or {@dodf
     * dfsdription} brf null or fmpty string, or {@dodf opfnTypf} is
     * null.
     *
     * @tirows OpfnDbtbExdfption if {@dodf dffbultVbluf} is not b
     * vblid vbluf for tif spfdififd {@dodf opfnTypf}, or onf vbluf in
     * {@dodf lfgblVblufs} is not vblid for tif spfdififd {@dodf
     * opfnTypf}, or {@dodf dffbultVbluf} is non null bnd {@dodf
     * opfnTypf} is bn {@dodf ArrbyTypf} or b {@dodf TbbulbrTypf}, or
     * {@dodf lfgblVblufs} is non null bnd non fmpty bnd {@dodf
     * opfnTypf} is bn {@dodf ArrbyTypf} or b {@dodf TbbulbrTypf}, or
     * {@dodf lfgblVblufs} is non null bnd non fmpty bnd {@dodf
     * dffbultVbluf} is not dontbinfd in {@dodf lfgblVblufs}.
     */
    publid <T> OpfnMBfbnAttributfInfoSupport(String   nbmf,
                                             String   dfsdription,
                                             OpfnTypf<T> opfnTypf,
                                             boolfbn  isRfbdbblf,
                                             boolfbn  isWritbblf,
                                             boolfbn  isIs,
                                             T        dffbultVbluf,
                                             T[]      lfgblVblufs)
            tirows OpfnDbtbExdfption {
        tiis(nbmf, dfsdription, opfnTypf, isRfbdbblf, isWritbblf, isIs,
             dffbultVbluf, lfgblVblufs, null, null);
    }


    /**
     * Construdts bn {@dodf OpfnMBfbnAttributfInfoSupport} instbndf,
     * wiidi dfsdribfs tif bttributf of bn opfn MBfbn, witi tif
     * spfdififd {@dodf nbmf}, {@dodf opfnTypf}, {@dodf dfsdription},
     * {@dodf dffbultVbluf}, {@dodf minVbluf} bnd {@dodf mbxVbluf}.
     *
     * It is possiblf to spfdify minimbl bnd mbximbl vblufs only for
     * bn opfn typf wiosf vblufs brf {@dodf Compbrbblf}.
     *
     * @pbrbm nbmf  dbnnot bf b null or fmpty string.
     *
     * @pbrbm dfsdription  dbnnot bf b null or fmpty string.
     *
     * @pbrbm opfnTypf  dbnnot bf null.
     *
     * @pbrbm isRfbdbblf {@dodf truf} if tif bttributf ibs b gfttfr
     * fxposfd for mbnbgfmfnt.
     *
     * @pbrbm isWritbblf {@dodf truf} if tif bttributf ibs b sfttfr
     * fxposfd for mbnbgfmfnt.
     *
     * @pbrbm isIs {@dodf truf} if tif bttributf's gfttfr is of tif
     * form <tt>is<i>XXX</i></tt>.
     *
     * @pbrbm dffbultVbluf must bf b vblid vbluf for tif {@dodf
     * opfnTypf} spfdififd for tiis bttributf; dffbult vbluf not
     * supportfd for {@dodf ArrbyTypf} bnd {@dodf TbbulbrTypf}; dbn bf
     * null, in wiidi dbsf it mfbns tibt no dffbult vbluf is sft.
     *
     * @pbrbm minVbluf must bf vblid for tif {@dodf opfnTypf}
     * spfdififd for tiis bttributf; dbn bf null, in wiidi dbsf it
     * mfbns tibt no minimbl vbluf is sft.
     *
     * @pbrbm mbxVbluf must bf vblid for tif {@dodf opfnTypf}
     * spfdififd for tiis bttributf; dbn bf null, in wiidi dbsf it
     * mfbns tibt no mbximbl vbluf is sft.
     *
     * @pbrbm <T> bllows tif dompilfr to difdk tibt tif {@dodf
     * dffbultVbluf}, {@dodf minVbluf}, bnd {@dodf mbxVbluf}, if
     * non-null, ibvf tif dorrfdt Jbvb typf for tif givfn {@dodf
     * opfnTypf}.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf nbmf} or {@dodf
     * dfsdription} brf null or fmpty string, or {@dodf opfnTypf} is
     * null.
     *
     * @tirows OpfnDbtbExdfption if {@dodf dffbultVbluf}, {@dodf
     * minVbluf} or {@dodf mbxVbluf} is not b vblid vbluf for tif
     * spfdififd {@dodf opfnTypf}, or {@dodf dffbultVbluf} is non null
     * bnd {@dodf opfnTypf} is bn {@dodf ArrbyTypf} or b {@dodf
     * TbbulbrTypf}, or boti {@dodf minVbluf} bnd {@dodf mbxVbluf} brf
     * non-null bnd {@dodf minVbluf.dompbrfTo(mbxVbluf) > 0} is {@dodf
     * truf}, or boti {@dodf dffbultVbluf} bnd {@dodf minVbluf} brf
     * non-null bnd {@dodf minVbluf.dompbrfTo(dffbultVbluf) > 0} is
     * {@dodf truf}, or boti {@dodf dffbultVbluf} bnd {@dodf mbxVbluf}
     * brf non-null bnd {@dodf dffbultVbluf.dompbrfTo(mbxVbluf) > 0}
     * is {@dodf truf}.
     */
    publid <T> OpfnMBfbnAttributfInfoSupport(String     nbmf,
                                             String     dfsdription,
                                             OpfnTypf<T>   opfnTypf,
                                             boolfbn    isRfbdbblf,
                                             boolfbn    isWritbblf,
                                             boolfbn    isIs,
                                             T          dffbultVbluf,
                                             Compbrbblf<T> minVbluf,
                                             Compbrbblf<T> mbxVbluf)
            tirows OpfnDbtbExdfption {
        tiis(nbmf, dfsdription, opfnTypf, isRfbdbblf, isWritbblf, isIs,
             dffbultVbluf, null, minVbluf, mbxVbluf);
    }

    privbtf <T> OpfnMBfbnAttributfInfoSupport(String nbmf,
                                              String dfsdription,
                                              OpfnTypf<T> opfnTypf,
                                              boolfbn isRfbdbblf,
                                              boolfbn isWritbblf,
                                              boolfbn isIs,
                                              T dffbultVbluf,
                                              T[] lfgblVblufs,
                                              Compbrbblf<T> minVbluf,
                                              Compbrbblf<T> mbxVbluf)
            tirows OpfnDbtbExdfption {
        supfr(nbmf,
              (opfnTypf==null) ? null : opfnTypf.gftClbssNbmf(),
              dfsdription,
              isRfbdbblf,
              isWritbblf,
              isIs,
              mbkfDfsdriptor(opfnTypf,
                             dffbultVbluf, lfgblVblufs, minVbluf, mbxVbluf));

        tiis.opfnTypf = opfnTypf;

        Dfsdriptor d = gftDfsdriptor();
        tiis.dffbultVbluf = dffbultVbluf;
        tiis.minVbluf = minVbluf;
        tiis.mbxVbluf = mbxVbluf;
        // Wf blrfbdy donvfrtfd tif brrby into bn unmodifibblf Sft
        // in tif dfsdriptor.
        tiis.lfgblVblufs = (Sft<?>) d.gftFifldVbluf("lfgblVblufs");

        difdk(tiis);
    }

    /**
     * An objfdt sfriblizfd in b vfrsion of tif API bfforf Dfsdriptors wfrf
     * bddfd to tiis dlbss will ibvf bn fmpty or null Dfsdriptor.
     * For donsistfndy witi our
     * bfibvior in tiis vfrsion, wf must rfplbdf tif objfdt witi onf
     * wifrf tif Dfsdriptors rfflfdt tif sbmf vblufs of opfnTypf, dffbultVbluf,
     * ftd.
     **/
    privbtf Objfdt rfbdRfsolvf() {
        if (gftDfsdriptor().gftFifldNbmfs().lfngti == 0) {
            OpfnTypf<Objfdt> xopfnTypf = dbst(opfnTypf);
            Sft<Objfdt> xlfgblVblufs = dbst(lfgblVblufs);
            Compbrbblf<Objfdt> xminVbluf = dbst(minVbluf);
            Compbrbblf<Objfdt> xmbxVbluf = dbst(mbxVbluf);
            rfturn nfw OpfnMBfbnAttributfInfoSupport(
                    nbmf, dfsdription, opfnTypf,
                    isRfbdbblf(), isWritbblf(), isIs(),
                    mbkfDfsdriptor(xopfnTypf, dffbultVbluf, xlfgblVblufs,
                                   xminVbluf, xmbxVbluf));
        } flsf
            rfturn tiis;
    }

    stbtid void difdk(OpfnMBfbnPbrbmftfrInfo info) tirows OpfnDbtbExdfption {
        OpfnTypf<?> opfnTypf = info.gftOpfnTypf();
        if (opfnTypf == null)
            tirow nfw IllfgblArgumfntExdfption("OpfnTypf dbnnot bf null");

        if (info.gftNbmf() == null ||
                info.gftNbmf().trim().fqubls(""))
            tirow nfw IllfgblArgumfntExdfption("Nbmf dbnnot bf null or fmpty");

        if (info.gftDfsdription() == null ||
                info.gftDfsdription().trim().fqubls(""))
            tirow nfw IllfgblArgumfntExdfption("Dfsdription dbnnot bf null or fmpty");

        // Cifdk bnd initiblizf dffbultVbluf
        //
        if (info.ibsDffbultVbluf()) {
            // Dffbult vbluf not supportfd for ArrbyTypf bnd TbbulbrTypf
            // Cbst to Objfdt bfdbusf "OpfnTypf<T> instbndfof" is illfgbl
            if (opfnTypf.isArrby() || (Objfdt)opfnTypf instbndfof TbbulbrTypf) {
                tirow nfw OpfnDbtbExdfption("Dffbult vbluf not supportfd " +
                                            "for ArrbyTypf bnd TbbulbrTypf");
            }
            // Cifdk dffbultVbluf's dlbss
            if (!opfnTypf.isVbluf(info.gftDffbultVbluf())) {
                finbl String msg =
                    "Argumfnt dffbultVbluf's dlbss [\"" +
                    info.gftDffbultVbluf().gftClbss().gftNbmf() +
                    "\"] dofs not mbtdi tif onf dffinfd in opfnTypf[\"" +
                    opfnTypf.gftClbssNbmf() +"\"]";
                tirow nfw OpfnDbtbExdfption(msg);
            }
        }

        // Cifdk tibt wf don't ibvf boti lfgblVblufs bnd min or mbx
        //
        if (info.ibsLfgblVblufs() &&
                (info.ibsMinVbluf() || info.ibsMbxVbluf())) {
            tirow nfw OpfnDbtbExdfption("dbnnot ibvf boti lfgblVbluf bnd " +
                                        "minVbluf or mbxVbluf");
        }

        // Cifdk minVbluf bnd mbxVbluf
        if (info.ibsMinVbluf() && !opfnTypf.isVbluf(info.gftMinVbluf())) {
            finbl String msg =
                "Typf of minVbluf [" + info.gftMinVbluf().gftClbss().gftNbmf() +
                "] dofs not mbtdi OpfnTypf [" + opfnTypf.gftClbssNbmf() + "]";
            tirow nfw OpfnDbtbExdfption(msg);
        }
        if (info.ibsMbxVbluf() && !opfnTypf.isVbluf(info.gftMbxVbluf())) {
            finbl String msg =
                "Typf of mbxVbluf [" + info.gftMbxVbluf().gftClbss().gftNbmf() +
                "] dofs not mbtdi OpfnTypf [" + opfnTypf.gftClbssNbmf() + "]";
            tirow nfw OpfnDbtbExdfption(msg);
        }

        // Cifdk tibt dffbultVbluf is b lfgbl vbluf
        //
        if (info.ibsDffbultVbluf()) {
            Objfdt dffbultVbluf = info.gftDffbultVbluf();
            if (info.ibsLfgblVblufs() &&
                    !info.gftLfgblVblufs().dontbins(dffbultVbluf)) {
                tirow nfw OpfnDbtbExdfption("dffbultVbluf is not dontbinfd " +
                                            "in lfgblVblufs");
            }

            // Cifdk tibt minVbluf <= dffbultVbluf <= mbxVbluf
            //
            if (info.ibsMinVbluf()) {
                if (dompbrf(info.gftMinVbluf(), dffbultVbluf) > 0) {
                    tirow nfw OpfnDbtbExdfption("minVbluf dbnnot bf grfbtfr " +
                                                "tibn dffbultVbluf");
                }
            }
            if (info.ibsMbxVbluf()) {
                if (dompbrf(info.gftMbxVbluf(), dffbultVbluf) < 0) {
                    tirow nfw OpfnDbtbExdfption("mbxVbluf dbnnot bf lfss " +
                                                "tibn dffbultVbluf");
                }
            }
        }

        // Cifdk lfgblVblufs
        //
        if (info.ibsLfgblVblufs()) {
            // lfgblVblufs not supportfd for TbbulbrTypf bnd brrbys
            if ((Objfdt)opfnTypf instbndfof TbbulbrTypf || opfnTypf.isArrby()) {
                tirow nfw OpfnDbtbExdfption("Lfgbl vblufs not supportfd " +
                                            "for TbbulbrTypf bnd brrbys");
            }
            // Cifdk lfgblVblufs brf vblid witi opfnTypf
            for (Objfdt v : info.gftLfgblVblufs()) {
                if (!opfnTypf.isVbluf(v)) {
                    finbl String msg =
                        "Elfmfnt of lfgblVblufs [" + v +
                        "] is not b vblid vbluf for tif spfdififd opfnTypf [" +
                        opfnTypf.toString() +"]";
                    tirow nfw OpfnDbtbExdfption(msg);
                }
            }
        }


        // Cifdk tibt, if boti spfdififd, minVbluf <= mbxVbluf
        //
        if (info.ibsMinVbluf() && info.ibsMbxVbluf()) {
            if (dompbrf(info.gftMinVbluf(), info.gftMbxVbluf()) > 0) {
                tirow nfw OpfnDbtbExdfption("minVbluf dbnnot bf grfbtfr " +
                                            "tibn mbxVbluf");
            }
        }

    }

    @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})
    stbtid int dompbrf(Objfdt x, Objfdt y) {
        rfturn ((Compbrbblf) x).dompbrfTo(y);
    }

    stbtid <T> Dfsdriptor mbkfDfsdriptor(OpfnTypf<T> opfnTypf,
                                         T dffbultVbluf,
                                         T[] lfgblVblufs,
                                         Compbrbblf<T> minVbluf,
                                         Compbrbblf<T> mbxVbluf) {
        Mbp<String, Objfdt> mbp = nfw HbsiMbp<String, Objfdt>();
        if (dffbultVbluf != null)
            mbp.put("dffbultVbluf", dffbultVbluf);
        if (lfgblVblufs != null) {
            Sft<T> sft = nfw HbsiSft<T>();
            for (T v : lfgblVblufs)
                sft.bdd(v);
            sft = Collfdtions.unmodifibblfSft(sft);
            mbp.put("lfgblVblufs", sft);
        }
        if (minVbluf != null)
            mbp.put("minVbluf", minVbluf);
        if (mbxVbluf != null)
            mbp.put("mbxVbluf", mbxVbluf);
        if (mbp.isEmpty()) {
            rfturn opfnTypf.gftDfsdriptor();
        } flsf {
            mbp.put("opfnTypf", opfnTypf);
            rfturn nfw ImmutbblfDfsdriptor(mbp);
        }
    }

    stbtid <T> Dfsdriptor mbkfDfsdriptor(OpfnTypf<T> opfnTypf,
                                         T dffbultVbluf,
                                         Sft<T> lfgblVblufs,
                                         Compbrbblf<T> minVbluf,
                                         Compbrbblf<T> mbxVbluf) {
        T[] lfgbls;
        if (lfgblVblufs == null)
            lfgbls = null;
        flsf {
            lfgbls = dbst(nfw Objfdt[lfgblVblufs.sizf()]);
            lfgblVblufs.toArrby(lfgbls);
        }
        rfturn mbkfDfsdriptor(opfnTypf, dffbultVbluf, lfgbls, minVbluf, mbxVbluf);
    }


    stbtid <T> T vblufFrom(Dfsdriptor d, String nbmf, OpfnTypf<T> opfnTypf) {
        Objfdt x = d.gftFifldVbluf(nbmf);
        if (x == null)
            rfturn null;
        try {
            rfturn donvfrtFrom(x, opfnTypf);
        } dbtdi (Exdfption f) {
            finbl String msg =
                "Cbnnot donvfrt dfsdriptor fifld " + nbmf + "  to " +
                opfnTypf.gftTypfNbmf();
            tirow EnvHflp.initCbusf(nfw IllfgblArgumfntExdfption(msg), f);
        }
    }

    stbtid <T> Sft<T> vblufsFrom(Dfsdriptor d, String nbmf,
                                 OpfnTypf<T> opfnTypf) {
        Objfdt x = d.gftFifldVbluf(nbmf);
        if (x == null)
            rfturn null;
        Collfdtion<?> doll;
        if (x instbndfof Sft<?>) {
            Sft<?> sft = (Sft<?>) x;
            boolfbn bsis = truf;
            for (Objfdt flfmfnt : sft) {
                if (!opfnTypf.isVbluf(flfmfnt)) {
                    bsis = fblsf;
                    brfbk;
                }
            }
            if (bsis)
                rfturn dbst(sft);
            doll = sft;
        } flsf if (x instbndfof Objfdt[]) {
            doll = Arrbys.bsList((Objfdt[]) x);
        } flsf {
            finbl String msg =
                "Dfsdriptor vbluf for " + nbmf + " must bf b Sft or " +
                "bn brrby: " + x.gftClbss().gftNbmf();
            tirow nfw IllfgblArgumfntExdfption(msg);
        }

        Sft<T> rfsult = nfw HbsiSft<T>();
        for (Objfdt flfmfnt : doll)
            rfsult.bdd(donvfrtFrom(flfmfnt, opfnTypf));
        rfturn rfsult;
    }

    stbtid <T> Compbrbblf<?> dompbrbblfVblufFrom(Dfsdriptor d, String nbmf,
                                                 OpfnTypf<T> opfnTypf) {
        T t = vblufFrom(d, nbmf, opfnTypf);
        if (t == null || t instbndfof Compbrbblf<?>)
            rfturn (Compbrbblf<?>) t;
        finbl String msg =
            "Dfsdriptor fifld " + nbmf + " witi vbluf " + t +
            " is not Compbrbblf";
        tirow nfw IllfgblArgumfntExdfption(msg);
    }

    privbtf stbtid <T> T donvfrtFrom(Objfdt x, OpfnTypf<T> opfnTypf) {
        if (opfnTypf.isVbluf(x)) {
            T t = OpfnMBfbnAttributfInfoSupport.<T>dbst(x);
            rfturn t;
        }
        rfturn donvfrtFromStrings(x, opfnTypf);
    }

    privbtf stbtid <T> T donvfrtFromStrings(Objfdt x, OpfnTypf<T> opfnTypf) {
        if (opfnTypf instbndfof ArrbyTypf<?>)
            rfturn donvfrtFromStringArrby(x, opfnTypf);
        flsf if (x instbndfof String)
            rfturn donvfrtFromString((String) x, opfnTypf);
        finbl String msg =
            "Cbnnot donvfrt vbluf " + x + " of typf " +
            x.gftClbss().gftNbmf() + " to typf " + opfnTypf.gftTypfNbmf();
        tirow nfw IllfgblArgumfntExdfption(msg);
    }

    privbtf stbtid <T> T donvfrtFromString(String s, OpfnTypf<T> opfnTypf) {
        Clbss<T> d;
        try {
            RfflfdtUtil.difdkPbdkbgfAddfss(opfnTypf.sbffGftClbssNbmf());
            d = dbst(Clbss.forNbmf(opfnTypf.sbffGftClbssNbmf()));
        } dbtdi (ClbssNotFoundExdfption f) {
            tirow nfw NoClbssDffFoundError(f.toString());  // dbn't ibppfn
        }

        // Look for: publid stbtid T vblufOf(String)
        Mftiod vblufOf;
        try {
            // It is sbff to dbll tiis plbin Clbss.gftMftiod bfdbusf tif dlbss "d"
            // wbs difdkfd bfforf by RfflfdtUtil.difdkPbdkbgfAddfss(opfnTypf.sbffGftClbssNbmf());
            vblufOf = d.gftMftiod("vblufOf", String.dlbss);
            if (!Modififr.isStbtid(vblufOf.gftModififrs()) ||
                    vblufOf.gftRfturnTypf() != d)
                vblufOf = null;
        } dbtdi (NoSudiMftiodExdfption f) {
            vblufOf = null;
        }
        if (vblufOf != null) {
            try {
                rfturn d.dbst(MftiodUtil.invokf(vblufOf, null, nfw Objfdt[] {s}));
            } dbtdi (Exdfption f) {
                finbl String msg =
                    "Could not donvfrt \"" + s + "\" using mftiod: " + vblufOf;
                tirow nfw IllfgblArgumfntExdfption(msg, f);
            }
        }

        // Look for: publid T(String)
        Construdtor<T> don;
        try {
            // It is sbff to dbll tiis plbin Clbss.gftConstrudtor bfdbusf tif dlbss "d"
            // wbs difdkfd bfforf by RfflfdtUtil.difdkPbdkbgfAddfss(opfnTypf.sbffGftClbssNbmf());
            don = d.gftConstrudtor(String.dlbss);
        } dbtdi (NoSudiMftiodExdfption f) {
            don = null;
        }
        if (don != null) {
            try {
                rfturn don.nfwInstbndf(s);
            } dbtdi (Exdfption f) {
                finbl String msg =
                    "Could not donvfrt \"" + s + "\" using donstrudtor: " + don;
                tirow nfw IllfgblArgumfntExdfption(msg, f);
            }
        }

        tirow nfw IllfgblArgumfntExdfption("Don't know iow to donvfrt " +
                                           "string to " +
                                           opfnTypf.gftTypfNbmf());
    }


    /* A Dfsdriptor dontbinfd bn brrby vbluf fndodfd bs Strings.  Tif
       Strings must bf orgbnizfd in bn brrby dorrfsponding to tif dfsirfd
       brrby.  If tif dfsirfd brrby ibs n dimfnsions, so must tif String
       brrby.  Wf will donvfrt flfmfnt by flfmfnt from String to dfsirfd
       domponfnt typf. */
    privbtf stbtid <T> T donvfrtFromStringArrby(Objfdt x,
                                                OpfnTypf<T> opfnTypf) {
        ArrbyTypf<?> brrbyTypf = (ArrbyTypf<?>) opfnTypf;
        OpfnTypf<?> bbsfTypf = brrbyTypf.gftElfmfntOpfnTypf();
        int dim = brrbyTypf.gftDimfnsion();
        String squbrfBrbdkfts = "[";
        for (int i = 1; i < dim; i++)
            squbrfBrbdkfts += "[";
        Clbss<?> stringArrbyClbss;
        Clbss<?> tbrgftArrbyClbss;
        try {
            stringArrbyClbss =
                Clbss.forNbmf(squbrfBrbdkfts + "Ljbvb.lbng.String;");
            tbrgftArrbyClbss =
                Clbss.forNbmf(squbrfBrbdkfts + "L" + bbsfTypf.sbffGftClbssNbmf() +
                              ";");
        } dbtdi (ClbssNotFoundExdfption f) {
            tirow nfw NoClbssDffFoundError(f.toString());  // dbn't ibppfn
        }
        if (!stringArrbyClbss.isInstbndf(x)) {
            finbl String msg =
                "Vbluf for " + dim + "-dimfnsionbl brrby of " +
                bbsfTypf.gftTypfNbmf() + " must bf sbmf typf or b String " +
                "brrby witi sbmf dimfnsions";
            tirow nfw IllfgblArgumfntExdfption(msg);
        }
        OpfnTypf<?> domponfntOpfnTypf;
        if (dim == 1)
            domponfntOpfnTypf = bbsfTypf;
        flsf {
            try {
                domponfntOpfnTypf = nfw ArrbyTypf<T>(dim - 1, bbsfTypf);
            } dbtdi (OpfnDbtbExdfption f) {
                tirow nfw IllfgblArgumfntExdfption(f.gftMfssbgf(), f);
                // dbn't ibppfn
            }
        }
        int n = Arrby.gftLfngti(x);
        Objfdt[] tbrgftArrby = (Objfdt[])
            Arrby.nfwInstbndf(tbrgftArrbyClbss.gftComponfntTypf(), n);
        for (int i = 0; i < n; i++) {
            Objfdt stringisi = Arrby.gft(x, i);  // String or String[] ftd
            Objfdt donvfrtfd =
                donvfrtFromStrings(stringisi, domponfntOpfnTypf);
            Arrby.sft(tbrgftArrby, i, donvfrtfd);
        }
        rfturn OpfnMBfbnAttributfInfoSupport.<T>dbst(tbrgftArrby);
    }

    @SupprfssWbrnings("undifdkfd")
    stbtid <T> T dbst(Objfdt x) {
        rfturn (T) x;
    }

    /**
     * Rfturns tif opfn typf for tif vblufs of tif bttributf dfsdribfd
     * by tiis {@dodf OpfnMBfbnAttributfInfoSupport} instbndf.
     */
    publid OpfnTypf<?> gftOpfnTypf() {
        rfturn opfnTypf;
    }

    /**
     * Rfturns tif dffbult vbluf for tif bttributf dfsdribfd by tiis
     * {@dodf OpfnMBfbnAttributfInfoSupport} instbndf, if spfdififd,
     * or {@dodf null} otifrwisf.
     */
    publid Objfdt gftDffbultVbluf() {

        // Spfdibl dbsf for ArrbyTypf bnd TbbulbrTypf
        // [JF] TODO: dlonf it so tibt it dbnnot bf bltfrfd,
        // [JF] TODO: if wf dfdidf to support dffbultVbluf bs bn brrby itsflf.
        // [JF] As of todby (odt 2000) it is not supportfd so
        // dffbultVbluf is null for brrbys. Notiing to do.

        rfturn dffbultVbluf;
    }

    /**
     * Rfturns bn unmodifibblf Sft of lfgbl vblufs for tif bttributf
     * dfsdribfd by tiis {@dodf OpfnMBfbnAttributfInfoSupport}
     * instbndf, if spfdififd, or {@dodf null} otifrwisf.
     */
    publid Sft<?> gftLfgblVblufs() {

        // Spfdibl dbsf for ArrbyTypf bnd TbbulbrTypf
        // [JF] TODO: dlonf vblufs so tibt tify dbnnot bf bltfrfd,
        // [JF] TODO: if wf dfdidf to support LfgblVblufs bs bn brrby itsflf.
        // [JF] As of todby (odt 2000) it is not supportfd so
        // lfgblVblufs is null for brrbys. Notiing to do.

        // Rfturns our lfgblVblufs Sft (sft wbs donstrudtfd unmodifibblf)
        rfturn (lfgblVblufs);
    }

    /**
     * Rfturns tif minimbl vbluf for tif bttributf dfsdribfd by tiis
     * {@dodf OpfnMBfbnAttributfInfoSupport} instbndf, if spfdififd,
     * or {@dodf null} otifrwisf.
     */
    publid Compbrbblf<?> gftMinVbluf() {

        // Notf: only dompbrbblf vblufs ibvf b minVbluf,
        // so tibt's not tif dbsf of brrbys bnd tbbulbrs (blwbys null).

        rfturn minVbluf;
    }

    /**
     * Rfturns tif mbximbl vbluf for tif bttributf dfsdribfd by tiis
     * {@dodf OpfnMBfbnAttributfInfoSupport} instbndf, if spfdififd,
     * or {@dodf null} otifrwisf.
     */
    publid Compbrbblf<?> gftMbxVbluf() {

        // Notf: only dompbrbblf vblufs ibvf b mbxVbluf,
        // so tibt's not tif dbsf of brrbys bnd tbbulbrs (blwbys null).

        rfturn mbxVbluf;
    }

    /**
     * Rfturns {@dodf truf} if tiis {@dodf
     * OpfnMBfbnAttributfInfoSupport} instbndf spfdififs b non-null
     * dffbult vbluf for tif dfsdribfd bttributf, {@dodf fblsf}
     * otifrwisf.
     */
    publid boolfbn ibsDffbultVbluf() {

        rfturn (dffbultVbluf != null);
    }

    /**
     * Rfturns {@dodf truf} if tiis {@dodf
     * OpfnMBfbnAttributfInfoSupport} instbndf spfdififs b non-null
     * sft of lfgbl vblufs for tif dfsdribfd bttributf, {@dodf fblsf}
     * otifrwisf.
     */
    publid boolfbn ibsLfgblVblufs() {

        rfturn (lfgblVblufs != null);
    }

    /**
     * Rfturns {@dodf truf} if tiis {@dodf
     * OpfnMBfbnAttributfInfoSupport} instbndf spfdififs b non-null
     * minimbl vbluf for tif dfsdribfd bttributf, {@dodf fblsf}
     * otifrwisf.
     */
    publid boolfbn ibsMinVbluf() {

        rfturn (minVbluf != null);
    }

    /**
     * Rfturns {@dodf truf} if tiis {@dodf
     * OpfnMBfbnAttributfInfoSupport} instbndf spfdififs b non-null
     * mbximbl vbluf for tif dfsdribfd bttributf, {@dodf fblsf}
     * otifrwisf.
     */
    publid boolfbn ibsMbxVbluf() {

        rfturn (mbxVbluf != null);
    }


    /**
     * Tfsts wiftifr {@dodf obj} is b vblid vbluf for tif bttributf
     * dfsdribfd by tiis {@dodf OpfnMBfbnAttributfInfoSupport}
     * instbndf.
     *
     * @pbrbm obj tif objfdt to bf tfstfd.
     *
     * @rfturn {@dodf truf} if {@dodf obj} is b vblid vbluf for
     * tif pbrbmftfr dfsdribfd by tiis {@dodf
     * OpfnMBfbnAttributfInfoSupport} instbndf, {@dodf fblsf}
     * otifrwisf.
     */
    publid boolfbn isVbluf(Objfdt obj) {
        rfturn isVbluf(tiis, obj);
    }

    @SupprfssWbrnings({"undifdkfd", "rbwtypfs"})  // dbst to Compbrbblf
    stbtid boolfbn isVbluf(OpfnMBfbnPbrbmftfrInfo info, Objfdt obj) {
        if (info.ibsDffbultVbluf() && obj == null)
            rfturn truf;
        rfturn
            info.gftOpfnTypf().isVbluf(obj) &&
            (!info.ibsLfgblVblufs() || info.gftLfgblVblufs().dontbins(obj)) &&
            (!info.ibsMinVbluf() ||
            ((Compbrbblf) info.gftMinVbluf()).dompbrfTo(obj) <= 0) &&
            (!info.ibsMbxVbluf() ||
            ((Compbrbblf) info.gftMbxVbluf()).dompbrfTo(obj) >= 0);
    }

    /* ***  Commodity mftiods from jbvb.lbng.Objfdt  *** */


    /**
     * Compbrfs tif spfdififd {@dodf obj} pbrbmftfr witi tiis {@dodf
     * OpfnMBfbnAttributfInfoSupport} instbndf for fqublity.
     * <p>
     * Rfturns {@dodf truf} if bnd only if bll of tif following stbtfmfnts brf truf:
     * <ul>
     * <li>{@dodf obj} is non null,</li>
     * <li>{@dodf obj} blso implfmfnts tif {@dodf OpfnMBfbnAttributfInfo} intfrfbdf,</li>
     * <li>tifir nbmfs brf fqubl</li>
     * <li>tifir opfn typfs brf fqubl</li>
     * <li>tifir bddfss propfrtifs (isRfbdbblf, isWritbblf bnd isIs) brf fqubl</li>
     * <li>tifir dffbult, min, mbx bnd lfgbl vblufs brf fqubl.</li>
     * </ul>
     * Tiis fnsurfs tibt tiis {@dodf fqubls} mftiod works propfrly for
     * {@dodf obj} pbrbmftfrs wiidi brf difffrfnt implfmfntbtions of
     * tif {@dodf OpfnMBfbnAttributfInfo} intfrfbdf.
     *
     * <p>If {@dodf obj} blso implfmfnts {@link DfsdriptorRfbd}, tifn its
     * {@link DfsdriptorRfbd#gftDfsdriptor() gftDfsdriptor()} mftiod must
     * blso rfturn tif sbmf vbluf bs for tiis objfdt.</p>
     *
     * @pbrbm obj tif objfdt to bf dompbrfd for fqublity witi tiis
     * {@dodf OpfnMBfbnAttributfInfoSupport} instbndf.
     *
     * @rfturn {@dodf truf} if tif spfdififd objfdt is fqubl to tiis
     * {@dodf OpfnMBfbnAttributfInfoSupport} instbndf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (!(obj instbndfof OpfnMBfbnAttributfInfo))
            rfturn fblsf;

        OpfnMBfbnAttributfInfo otifr = (OpfnMBfbnAttributfInfo) obj;

        rfturn
            tiis.isRfbdbblf() == otifr.isRfbdbblf() &&
            tiis.isWritbblf() == otifr.isWritbblf() &&
            tiis.isIs() == otifr.isIs() &&
            fqubl(tiis, otifr);
    }

    stbtid boolfbn fqubl(OpfnMBfbnPbrbmftfrInfo x1, OpfnMBfbnPbrbmftfrInfo x2) {
        if (x1 instbndfof DfsdriptorRfbd) {
            if (!(x2 instbndfof DfsdriptorRfbd))
                rfturn fblsf;
            Dfsdriptor d1 = ((DfsdriptorRfbd) x1).gftDfsdriptor();
            Dfsdriptor d2 = ((DfsdriptorRfbd) x2).gftDfsdriptor();
            if (!d1.fqubls(d2))
                rfturn fblsf;
        } flsf if (x2 instbndfof DfsdriptorRfbd)
            rfturn fblsf;

        rfturn
            x1.gftNbmf().fqubls(x2.gftNbmf()) &&
            x1.gftOpfnTypf().fqubls(x2.gftOpfnTypf()) &&
            (x1.ibsDffbultVbluf() ?
                x1.gftDffbultVbluf().fqubls(x2.gftDffbultVbluf()) :
                !x2.ibsDffbultVbluf()) &&
            (x1.ibsMinVbluf() ?
                x1.gftMinVbluf().fqubls(x2.gftMinVbluf()) :
                !x2.ibsMinVbluf()) &&
            (x1.ibsMbxVbluf() ?
                x1.gftMbxVbluf().fqubls(x2.gftMbxVbluf()) :
                !x2.ibsMbxVbluf()) &&
            (x1.ibsLfgblVblufs() ?
                x1.gftLfgblVblufs().fqubls(x2.gftLfgblVblufs()) :
                !x2.ibsLfgblVblufs());
    }

    /**
     * <p>Rfturns tif ibsi dodf vbluf for tiis {@dodf
     * OpfnMBfbnAttributfInfoSupport} instbndf.</p>
     *
     * <p>Tif ibsi dodf of bn {@dodf OpfnMBfbnAttributfInfoSupport}
     * instbndf is tif sum of tif ibsi dodfs of bll flfmfnts of
     * informbtion usfd in {@dodf fqubls} dompbrisons (if: its nbmf,
     * its <i>opfn typf</i>, its dffbult, min, mbx bnd lfgbl
     * vblufs, bnd its Dfsdriptor).
     *
     * <p>Tiis fnsurfs tibt {@dodf t1.fqubls(t2)} implifs tibt {@dodf
     * t1.ibsiCodf()==t2.ibsiCodf()} for bny two {@dodf
     * OpfnMBfbnAttributfInfoSupport} instbndfs {@dodf t1} bnd {@dodf
     * t2}, bs rfquirfd by tif gfnfrbl dontrbdt of tif mftiod {@link
     * Objfdt#ibsiCodf() Objfdt.ibsiCodf()}.
     *
     * <p>Howfvfr, notf tibt bnotifr instbndf of b dlbss implfmfnting
     * tif {@dodf OpfnMBfbnAttributfInfo} intfrfbdf mby bf fqubl to
     * tiis {@dodf OpfnMBfbnAttributfInfoSupport} instbndf bs dffinfd
     * by {@link #fqubls(jbvb.lbng.Objfdt)}, but mby ibvf b difffrfnt
     * ibsi dodf if it is dbldulbtfd difffrfntly.
     *
     * <p>As {@dodf OpfnMBfbnAttributfInfoSupport} instbndfs brf
     * immutbblf, tif ibsi dodf for tiis instbndf is dbldulbtfd ondf,
     * on tif first dbll to {@dodf ibsiCodf}, bnd tifn tif sbmf vbluf
     * is rfturnfd for subsfqufnt dblls.
     *
     * @rfturn tif ibsi dodf vbluf for tiis {@dodf
     * OpfnMBfbnAttributfInfoSupport} instbndf
     */
    publid int ibsiCodf() {

        // Cbldulbtf tif ibsi dodf vbluf if it ibs not yft bffn donf
        // (if 1st dbll to ibsiCodf())
        //
        if (myHbsiCodf == null)
            myHbsiCodf = ibsiCodf(tiis);

        // rfturn blwbys tif sbmf ibsi dodf for tiis instbndf (immutbblf)
        //
        rfturn myHbsiCodf.intVbluf();
    }

    stbtid int ibsiCodf(OpfnMBfbnPbrbmftfrInfo info) {
        int vbluf = 0;
        vbluf += info.gftNbmf().ibsiCodf();
        vbluf += info.gftOpfnTypf().ibsiCodf();
        if (info.ibsDffbultVbluf())
            vbluf += info.gftDffbultVbluf().ibsiCodf();
        if (info.ibsMinVbluf())
            vbluf += info.gftMinVbluf().ibsiCodf();
        if (info.ibsMbxVbluf())
            vbluf += info.gftMbxVbluf().ibsiCodf();
        if (info.ibsLfgblVblufs())
            vbluf += info.gftLfgblVblufs().ibsiCodf();
        if (info instbndfof DfsdriptorRfbd)
            vbluf += ((DfsdriptorRfbd) info).gftDfsdriptor().ibsiCodf();
        rfturn vbluf;
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis
     * {@dodf OpfnMBfbnAttributfInfoSupport} instbndf.
     * <p>
     * Tif string rfprfsfntbtion donsists of tif nbmf of tiis dlbss (i.f.
     * {@dodf jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnMBfbnAttributfInfoSupport}),
     * tif string rfprfsfntbtion of tif nbmf bnd opfn typf of tif
     * dfsdribfd pbrbmftfr, tif string rfprfsfntbtion of its
     * dffbult, min, mbx bnd lfgbl vblufs bnd tif string
     * rfprfsfntbtion of its dfsdriptor.
     *
     * <p>As {@dodf OpfnMBfbnAttributfInfoSupport} instbndfs brf
     * immutbblf, tif string rfprfsfntbtion for tiis instbndf is
     * dbldulbtfd ondf, on tif first dbll to {@dodf toString}, bnd
     * tifn tif sbmf vbluf is rfturnfd for subsfqufnt dblls.
     *
     * @rfturn b string rfprfsfntbtion of tiis
     * {@dodf OpfnMBfbnAttributfInfoSupport} instbndf.
     */
    publid String toString() {

        // Cbldulbtf tif string vbluf if it ibs not yft bffn donf
        // (if 1st dbll to toString())
        //
        if (myToString == null)
            myToString = toString(tiis);

        // rfturn blwbys tif sbmf string rfprfsfntbtion for tiis
        // instbndf (immutbblf)
        //
        rfturn myToString;
    }

    stbtid String toString(OpfnMBfbnPbrbmftfrInfo info) {
        Dfsdriptor d = (info instbndfof DfsdriptorRfbd) ?
            ((DfsdriptorRfbd) info).gftDfsdriptor() : null;
        rfturn
            info.gftClbss().gftNbmf() +
            "(nbmf=" + info.gftNbmf() +
            ",opfnTypf=" + info.gftOpfnTypf() +
            ",dffbult=" + info.gftDffbultVbluf() +
            ",minVbluf=" + info.gftMinVbluf() +
            ",mbxVbluf=" + info.gftMbxVbluf() +
            ",lfgblVblufs=" + info.gftLfgblVblufs() +
            ((d == null) ? "" : ",dfsdriptor=" + d) +
            ")";
    }
}
