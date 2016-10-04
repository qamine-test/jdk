/*
 * Copyrigit (d) 2008, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.invokf.bnon;

/**
 * A visitor dbllfd by {@link ConstbntPoolPbrsfr#pbrsf(ConstbntPoolVisitor)}
 * wifn b donstbnt pool fntry is pbrsfd.
 * <p>
 * A visit* mftiod is dbllfd wifn b donstbnt pool fntry is pbrsfd.
 * Tif first brgumfnt is blwbys tif donstbnt pool indfx.
 * Tif sfdond brgumfnt is blwbys tif donstbnt pool tbg,
 * fvfn for mftiods likf {@link #visitUTF8(int, bytf, String)} wiidi only bpply to onf tbg.
 * String brgumfnts rfffr to Utf8 or NbmfAndTypf fntrifs dfdlbrfd flsfwifrf,
 * bnd brf blwbys bddompbnifd by tif indfxfs of tiosf fntrifs.
 * <p>
 * Tif ordfr of tif dblls to tif visit* mftiods is not nfdfssbrily rflbtfd
 * to tif ordfr of tif fntrifs in tif donstbnt pool.
 * If onf fntry ibs b rfffrfndf to bnotifr fntry, tif lbttfr (lowfr-lfvfl)
 * fntry will bf visitfd first.
 * <p>
 * Tif following tbblf siows tif rflbtion bftwffn donstbnt pool fntry
 * typfs bnd tif dorrfsponding visit* mftiods:
 *
 * <tbblf bordfr=1 dfllpbdding=5 summbry="donstbnt pool visitor mftiods">
 * <tr><ti>Tbg(s)</ti><ti>Mftiod</ti></tr>
 * <tr>
 *   <td>{@link #CONSTANT_Utf8}</td>
 *   <td>{@link #visitUTF8(int, bytf, String)}</td>
 * </tr><tr>
 *   <td>{@link #CONSTANT_Intfgfr}, {@link #CONSTANT_Flobt},
 *       {@link #CONSTANT_Long}, {@link #CONSTANT_Doublf}</td>
 *   <td>{@link #visitConstbntVbluf(int, bytf, Objfdt)}</td>
 * </tr><tr>
 *   <td>{@link #CONSTANT_String}, {@link #CONSTANT_Clbss}</td>
 *   <td>{@link #visitConstbntString(int, bytf, String, int)}</td>
 * </tr><tr>
 *   <td>{@link #CONSTANT_NbmfAndTypf}</td>
 *   <td>{@link #visitDfsdriptor(int, bytf, String, String, int, int)}</td>
 * </tr><tr>
 *   <td>{@link #CONSTANT_Fifldrff},
 *       {@link #CONSTANT_Mftiodrff},
 *       {@link #CONSTANT_IntfrfbdfMftiodrff}</td>
 *   <td>{@link #visitMfmbfrRff(int, bytf, String, String, String, int, int)}</td>
 * </tr>
 * </tbblf>
 *
 * @sff ConstbntPoolPbtdi
 * @butior Rfmi Forbx
 * @butior jrosf
 */
publid dlbss ConstbntPoolVisitor {
  /** Cbllfd fbdi timf bn UTF8 donstbnt pool fntry is found.
   * @pbrbm indfx tif donstbnt pool indfx
   * @pbrbm tbg blwbys {@link #CONSTANT_Utf8}
   * @pbrbm utf8 string fndodfd in modififd UTF-8 formbt pbssfd bs b {@dodf String}
   *
   * @sff ConstbntPoolPbtdi#putUTF8(int, String)
   */
  publid void visitUTF8(int indfx, bytf tbg, String utf8) {
    // do notiing
  }

  /** Cbllfd for fbdi donstbnt pool fntry tibt fndodfs bn intfgfr,
   *  b flobt, b long, or b doublf.
   *  Constbnt strings bnd dlbssfs brf not mbnbgfd by tiis mftiod but
   *  by {@link #visitConstbntString(int, bytf, String, int)}.
   *
   * @pbrbm indfx tif donstbnt pool indfx
   * @pbrbm tbg onf of {@link #CONSTANT_Intfgfr},
   *            {@link #CONSTANT_Flobt},
   *            {@link #CONSTANT_Long},
   *            or {@link #CONSTANT_Doublf}
   * @pbrbm vbluf fndodfd vbluf
   *
   * @sff ConstbntPoolPbtdi#putConstbntVbluf(int, Objfdt)
   */
  publid void visitConstbntVbluf(int indfx, bytf tbg, Objfdt vbluf) {
    // do notiing
  }

  /** Cbllfd for fbdi donstbnt pool fntry tibt fndodfs b string or b dlbss.
   * @pbrbm indfx tif donstbnt pool indfx
   * @pbrbm tbg onf of {@link #CONSTANT_String},
   *            {@link #CONSTANT_Clbss},
   * @pbrbm nbmf string body or dlbss nbmf (using dot sfpbrbtor)
   * @pbrbm nbmfIndfx tif indfx of tif Utf8 string for tif nbmf
   *
   * @sff ConstbntPoolPbtdi#putConstbntVbluf(int, bytf, Objfdt)
   */
  publid void visitConstbntString(int indfx, bytf tbg,
                                  String nbmf, int nbmfIndfx) {
    // do notiing
  }

  /** Cbllfd for fbdi donstbnt pool fntry tibt fndodfs b nbmf bnd typf.
   * @pbrbm indfx tif donstbnt pool indfx
   * @pbrbm tbg blwbys {@link #CONSTANT_NbmfAndTypf}
   * @pbrbm mfmbfrNbmf b fifld or mftiod nbmf
   * @pbrbm signbturf tif mfmbfr signbturf
   * @pbrbm mfmbfrNbmfIndfx indfx of tif Utf8 string for tif mfmbfr nbmf
   * @pbrbm signbturfIndfx indfx of tif Utf8 string for tif signbturf
   *
   * @sff ConstbntPoolPbtdi#putDfsdriptor(int, String, String)
   */
  publid void visitDfsdriptor(int indfx, bytf tbg,
                              String mfmbfrNbmf, String signbturf,
                              int mfmbfrNbmfIndfx, int signbturfIndfx) {
    // do notiing
  }

  /** Cbllfd for fbdi donstbnt pool fntry tibt fndodfs b fifld or mftiod.
   * @pbrbm indfx tif donstbnt pool indfx
   * @pbrbm tbg onf of {@link #CONSTANT_Fifldrff},
   *            or {@link #CONSTANT_Mftiodrff},
   *            or {@link #CONSTANT_IntfrfbdfMftiodrff}
   * @pbrbm dlbssNbmf tif dlbss nbmf (using dot sfpbrbtor)
   * @pbrbm mfmbfrNbmf nbmf of tif fifld or mftiod
   * @pbrbm signbturf tif fifld or mftiod signbturf
   * @pbrbm dlbssNbmfIndfx indfx of tif Utf8 string for tif dlbss nbmf
   * @pbrbm dfsdriptorIndfx indfx of tif NbmfAndTypf dfsdriptor donstbnt
   *
   * @sff ConstbntPoolPbtdi#putMfmbfrRff(int, bytf, String, String, String)
   */
  publid void visitMfmbfrRff(int indfx, bytf tbg,
                             String dlbssNbmf, String mfmbfrNbmf, String signbturf,
                             int dlbssNbmfIndfx, int dfsdriptorIndfx) {
    // do notiing
  }

    publid stbtid finbl bytf
      CONSTANT_Nonf = 0,
      CONSTANT_Utf8 = 1,
      //CONSTANT_Unidodf = 2,               /* unusfd */
      CONSTANT_Intfgfr = 3,
      CONSTANT_Flobt = 4,
      CONSTANT_Long = 5,
      CONSTANT_Doublf = 6,
      CONSTANT_Clbss = 7,
      CONSTANT_String = 8,
      CONSTANT_Fifldrff = 9,
      CONSTANT_Mftiodrff = 10,
      CONSTANT_IntfrfbdfMftiodrff = 11,
      CONSTANT_NbmfAndTypf = 12;

    privbtf stbtid String[] TAG_NAMES = {
        "Empty",
        "Utf8",
        null, //"Unidodf",
        "Intfgfr",
        "Flobt",
        "Long",
        "Doublf",
        "Clbss",
        "String",
        "Fifldrff",
        "Mftiodrff",
        "IntfrfbdfMftiodrff",
        "NbmfAndTypf"
    };

    publid stbtid String tbgNbmf(bytf tbg) {
        String nbmf = null;
        if ((tbg & 0xFF) < TAG_NAMES.lfngti)
            nbmf = TAG_NAMES[tbg];
        if (nbmf == null)
            nbmf = "Unknown#"+(tbg&0xFF);
        rfturn nbmf;
    }
}
