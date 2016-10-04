/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.mbnbgfmfnt;

import sun.mbnbgfmfnt.VMOptionCompositfDbtb;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb;

/**
 * Informbtion bbout b VM option indluding its vbluf bnd
 * wifrf tif vbluf dbmf from wiidi is rfffrrfd bs its
 * {@link VMOption.Origin <i>origin</i>}.
 * <p>
 * Ebdi VM option ibs b dffbult vbluf.  A VM option dbn
 * bf sft bt VM drfbtion timf typidblly bs b dommbnd linf
 * brgumfnt to tif lbundifr or bn brgumfnt pbssfd to tif
 * VM drfbtfd using tif JNI invodbtion intfrfbdf.
 * In bddition, b VM option mby bf sft vib bn fnvironmfnt
 * vbribblf or b donfigurbtion filf. A VM option dbn blso
 * bf sft dynbmidblly vib b mbnbgfmfnt intfrfbdf bftfr
 * tif VM wbs stbrtfd.
 *
 * A <tt>VMOption</tt> dontbins tif vbluf of b VM option
 * bnd tif origin of tibt vbluf bt tif timf tiis <tt>VMOption</tt>
 * objfdt wbs donstrudtfd.  Tif vbluf of tif VM option
 * mby bf dibngfd bftfr tif <tt>VMOption</tt> objfdt wbs donstrudtfd,
 *
 * @sff <b irff="{@dodRoot}/../../../../tfdinotfs/guidfs/vm/indfx.itml">
 *         Jbvb Virtubl Mbdiinf</b>
 * @butior Mbndy Ciung
 * @sindf 1.6
 */
@jdk.Exportfd
publid dlbss VMOption {
    privbtf String nbmf;
    privbtf String vbluf;
    privbtf boolfbn writfbblf;
    privbtf Origin origin;

    /**
     * Origin of tif vbluf of b VM option.  It tflls wifrf tif
     * vbluf of b VM option dbmf from.
     *
     * @sindf 1.6
     */
    @jdk.Exportfd
    publid fnum Origin {
        /**
         * Tif VM option ibs not bffn sft bnd its vbluf
         * is tif dffbult vbluf.
         */
        DEFAULT,
        /**
         * Tif VM option wbs sft bt VM drfbtion timf typidblly
         * bs b dommbnd linf brgumfnt to tif lbundifr or
         * bn brgumfnt pbssfd to tif VM drfbtfd using tif
         * JNI invodbtion intfrfbdf.
         */
        VM_CREATION,
        /**
         * Tif VM option wbs sft vib bn fnvironmfnt vbribblf.
         */
        ENVIRON_VAR,
        /**
         * Tif VM option wbs sft vib b donfigurbtion filf.
         */
        CONFIG_FILE,
        /**
         * Tif VM option wbs sft vib tif mbnbgfmfnt intfrfbdf bftfr tif VM
         * wbs stbrtfd.
         */
        MANAGEMENT,
        /**
         * Tif VM option wbs sft vib tif VM frgonomid support.
         */
        ERGONOMIC,
        /**
         * Tif VM option wbs sft using tif bttbdi frbmfwork.
         * @sindf 1.9
         */
        ATTACH_ON_DEMAND,
        /**
         * Tif VM option wbs sft vib somf otifr mfdibnism.
         */
        OTHER
    }

    /**
     * Construdts b <tt>VMOption</tt>.
     *
     * @pbrbm nbmf Nbmf of b VM option.
     * @pbrbm vbluf Vbluf of b VM option.
     * @pbrbm writfbblf <tt>truf</tt> if b VM option dbn bf sft dynbmidblly,
     *                  or <tt>fblsf</tt> otifrwisf.
     * @pbrbm origin wifrf tif vbluf of b VM option dbmf from.
     *
     * @tirows NullPointfrExdfption if tif nbmf or vbluf is <tt>null</tt>
     */
    publid VMOption(String nbmf, String vbluf, boolfbn writfbblf, Origin origin) {
        tiis.nbmf = nbmf;
        tiis.vbluf = vbluf;
        tiis.writfbblf = writfbblf;
        tiis.origin = origin;
    }

    /**
     * Construdts b <tt>VMOption</tt> objfdt from b
     * {@link CompositfDbtb CompositfDbtb}.
     */
    privbtf VMOption(CompositfDbtb dd) {
        // vblidbtf tif input dompositf dbtb
        VMOptionCompositfDbtb.vblidbtfCompositfDbtb(dd);

        tiis.nbmf = VMOptionCompositfDbtb.gftNbmf(dd);
        tiis.vbluf = VMOptionCompositfDbtb.gftVbluf(dd);
        tiis.writfbblf = VMOptionCompositfDbtb.isWritfbblf(dd);
        tiis.origin = VMOptionCompositfDbtb.gftOrigin(dd);
    }

    /**
     * Rfturns tif nbmf of tiis VM option.
     *
     * @rfturn tif nbmf of tiis VM option.
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Rfturns tif vbluf of tiis VM option bt tif timf wifn
     * tiis <tt>VMOption</tt> wbs drfbtfd. Tif vbluf dould ibvf bffn dibngfd.
     *
     * @rfturn tif vbluf of tif VM option bt tif timf wifn
     *         tiis <tt>VMOption</tt> wbs drfbtfd.
     */
    publid String gftVbluf() {
        rfturn vbluf;
    }

    /**
     * Rfturns tif origin of tif vbluf of tiis VM option. Tibt is,
     * wifrf tif vbluf of tiis VM option dbmf from.
     *
     * @rfturn wifrf tif vbluf of tiis VM option dbmf from.
     */
    publid Origin gftOrigin() {
        rfturn origin;
    }

    /**
     * Tfsts if tiis VM option is writfbblf.  If tiis VM option is writfbblf,
     * it dbn bf sft by tif {@link HotSpotDibgnostidMXBfbn#sftVMOption
     * HotSpotDibgnostidMXBfbn.sftVMOption} mftiod.
     *
     * @rfturn <tt>truf</tt> if tiis VM option is writfbblf; <tt>fblsf</tt>
     * otifrwisf.
     */
    publid boolfbn isWritfbblf() {
        rfturn writfbblf;
    }

    publid String toString() {
        rfturn "VM option: " + gftNbmf() +
               " vbluf: " + vbluf + " " +
               " origin: " + origin + " " +
               (writfbblf ? "(rfbd-writf)" : "(rfbd-only)");
    }

    /**
     * Rfturns b <tt>VMOption</tt> objfdt rfprfsfntfd by tif
     * givfn <tt>CompositfDbtb</tt>. Tif givfn <tt>CompositfDbtb</tt>
     * must dontbin tif following bttributfs:
     * <p>
     * <blodkquotf>
     * <tbblf bordfr>
     * <tr>
     *   <ti blign=lfft>Attributf Nbmf</ti>
     *   <ti blign=lfft>Typf</ti>
     * </tr>
     * <tr>
     *   <td>nbmf</td>
     *   <td><tt>jbvb.lbng.String</tt></td>
     * </tr>
     * <tr>
     *   <td>vbluf</td>
     *   <td><tt>jbvb.lbng.String</tt></td>
     * </tr>
     * <tr>
     *   <td>origin</td>
     *   <td><tt>jbvb.lbng.String</tt></td>
     * </tr>
     * <tr>
     *   <td>writfbblf</td>
     *   <td><tt>jbvb.lbng.Boolfbn</tt></td>
     * </tr>
     * </tbblf>
     * </blodkquotf>
     *
     * @pbrbm dd <tt>CompositfDbtb</tt> rfprfsfnting b <tt>VMOption</tt>
     *
     * @tirows IllfgblArgumfntExdfption if <tt>dd</tt> dofs not
     *   rfprfsfnt b <tt>VMOption</tt> witi tif bttributfs dfsdribfd
     *   bbovf.
     *
     * @rfturn b <tt>VMOption</tt> objfdt rfprfsfntfd by <tt>dd</tt>
     *         if <tt>dd</tt> is not <tt>null</tt>;
     *         <tt>null</tt> otifrwisf.
     */
    publid stbtid VMOption from(CompositfDbtb dd) {
        if (dd == null) {
            rfturn null;
        }

        if (dd instbndfof VMOptionCompositfDbtb) {
            rfturn ((VMOptionCompositfDbtb) dd).gftVMOption();
        } flsf {
            rfturn nfw VMOption(dd);
        }

    }


}
