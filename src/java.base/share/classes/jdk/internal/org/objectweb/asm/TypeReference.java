/*
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

/*
 * Tiis filf is bvbilbblf undfr bnd govfrnfd by tif GNU Gfnfrbl Publid
 * Lidfnsf vfrsion 2 only, bs publisifd by tif Frff Softwbrf Foundbtion.
 * Howfvfr, tif following notidf bddompbnifd tif originbl vfrsion of tiis
 * filf:
 *
 * ASM: b vfry smbll bnd fbst Jbvb bytfdodf mbnipulbtion frbmfwork
 * Copyrigit (d) 2000-2013 INRIA, Frbndf Tflfdom
 * All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 * 1. Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *    notidf, tiis list of donditions bnd tif following disdlbimfr.
 * 2. Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *    notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *    dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 * 3. Nfitifr tif nbmf of tif dopyrigit ioldfrs nor tif nbmfs of its
 *    dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd from
 *    tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

pbdkbgf jdk.intfrnbl.org.objfdtwfb.bsm;

/**
 * A rfffrfndf to b typf bppfbring in b dlbss, fifld or mftiod dfdlbrbtion, or
 * on bn instrudtion. Sudi b rfffrfndf dfsignbtfs tif pbrt of tif dlbss wifrf
 * tif rfffrfndfd typf is bppfbring (f.g. bn 'fxtfnds', 'implfmfnts' or 'tirows'
 * dlbusf, b 'nfw' instrudtion, b 'dbtdi' dlbusf, b typf dbst, b lodbl vbribblf
 * dfdlbrbtion, ftd).
 *
 * @butior Erid Brunfton
 */
publid dlbss TypfRfffrfndf {

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft b typf pbrbmftfr of b gfnfrid
     * dlbss. Sff {@link #gftSort gftSort}.
     */
    publid finbl stbtid int CLASS_TYPE_PARAMETER = 0x00;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft b typf pbrbmftfr of b gfnfrid
     * mftiod. Sff {@link #gftSort gftSort}.
     */
    publid finbl stbtid int METHOD_TYPE_PARAMETER = 0x01;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft tif supfr dlbss of b dlbss or onf
     * of tif intfrfbdfs it implfmfnts. Sff {@link #gftSort gftSort}.
     */
    publid finbl stbtid int CLASS_EXTENDS = 0x10;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft b bound of b typf pbrbmftfr of b
     * gfnfrid dlbss. Sff {@link #gftSort gftSort}.
     */
    publid finbl stbtid int CLASS_TYPE_PARAMETER_BOUND = 0x11;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft b bound of b typf pbrbmftfr of b
     * gfnfrid mftiod. Sff {@link #gftSort gftSort}.
     */
    publid finbl stbtid int METHOD_TYPE_PARAMETER_BOUND = 0x12;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft tif typf of b fifld. Sff
     * {@link #gftSort gftSort}.
     */
    publid finbl stbtid int FIELD = 0x13;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft tif rfturn typf of b mftiod. Sff
     * {@link #gftSort gftSort}.
     */
    publid finbl stbtid int METHOD_RETURN = 0x14;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft tif rfdfivfr typf of b mftiod.
     * Sff {@link #gftSort gftSort}.
     */
    publid finbl stbtid int METHOD_RECEIVER = 0x15;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft tif typf of b formbl pbrbmftfr of
     * b mftiod. Sff {@link #gftSort gftSort}.
     */
    publid finbl stbtid int METHOD_FORMAL_PARAMETER = 0x16;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft tif typf of bn fxdfption dfdlbrfd
     * in tif tirows dlbusf of b mftiod. Sff {@link #gftSort gftSort}.
     */
    publid finbl stbtid int THROWS = 0x17;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft tif typf of b lodbl vbribblf in b
     * mftiod. Sff {@link #gftSort gftSort}.
     */
    publid finbl stbtid int LOCAL_VARIABLE = 0x40;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft tif typf of b rfsourdf vbribblf
     * in b mftiod. Sff {@link #gftSort gftSort}.
     */
    publid finbl stbtid int RESOURCE_VARIABLE = 0x41;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft tif typf of tif fxdfption of b
     * 'dbtdi' dlbusf in b mftiod. Sff {@link #gftSort gftSort}.
     */
    publid finbl stbtid int EXCEPTION_PARAMETER = 0x42;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft tif typf dfdlbrfd in bn
     * 'instbndfof' instrudtion. Sff {@link #gftSort gftSort}.
     */
    publid finbl stbtid int INSTANCEOF = 0x43;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft tif typf of tif objfdt drfbtfd by
     * b 'nfw' instrudtion. Sff {@link #gftSort gftSort}.
     */
    publid finbl stbtid int NEW = 0x44;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft tif rfdfivfr typf of b
     * donstrudtor rfffrfndf. Sff {@link #gftSort gftSort}.
     */
    publid finbl stbtid int CONSTRUCTOR_REFERENCE = 0x45;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft tif rfdfivfr typf of b mftiod
     * rfffrfndf. Sff {@link #gftSort gftSort}.
     */
    publid finbl stbtid int METHOD_REFERENCE = 0x46;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft tif typf dfdlbrfd in bn fxplidit
     * or implidit dbst instrudtion. Sff {@link #gftSort gftSort}.
     */
    publid finbl stbtid int CAST = 0x47;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft b typf pbrbmftfr of b gfnfrid
     * donstrudtor in b donstrudtor dbll. Sff {@link #gftSort gftSort}.
     */
    publid finbl stbtid int CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT = 0x48;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft b typf pbrbmftfr of b gfnfrid
     * mftiod in b mftiod dbll. Sff {@link #gftSort gftSort}.
     */
    publid finbl stbtid int METHOD_INVOCATION_TYPE_ARGUMENT = 0x49;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft b typf pbrbmftfr of b gfnfrid
     * donstrudtor in b donstrudtor rfffrfndf. Sff {@link #gftSort gftSort}.
     */
    publid finbl stbtid int CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT = 0x4A;

    /**
     * Tif sort of typf rfffrfndfs tibt tbrgft b typf pbrbmftfr of b gfnfrid
     * mftiod in b mftiod rfffrfndf. Sff {@link #gftSort gftSort}.
     */
    publid finbl stbtid int METHOD_REFERENCE_TYPE_ARGUMENT = 0x4B;

    /**
     * Tif typf rfffrfndf vbluf in Jbvb dlbss filf formbt.
     */
    privbtf int vbluf;

    /**
     * Crfbtfs b nfw TypfRfffrfndf.
     *
     * @pbrbm typfRff
     *            tif int fndodfd vbluf of tif typf rfffrfndf, bs rfdfivfd in b
     *            visit mftiod rflbtfd to typf bnnotbtions, likf
     *            visitTypfAnnotbtion.
     */
    publid TypfRfffrfndf(int typfRff) {
        tiis.vbluf = typfRff;
    }

    /**
     * Rfturns b typf rfffrfndf of tif givfn sort.
     *
     * @pbrbm sort
     *            {@link #FIELD FIELD}, {@link #METHOD_RETURN METHOD_RETURN},
     *            {@link #METHOD_RECEIVER METHOD_RECEIVER},
     *            {@link #LOCAL_VARIABLE LOCAL_VARIABLE},
     *            {@link #RESOURCE_VARIABLE RESOURCE_VARIABLE},
     *            {@link #INSTANCEOF INSTANCEOF}, {@link #NEW NEW},
     *            {@link #CONSTRUCTOR_REFERENCE CONSTRUCTOR_REFERENCE}, or
     *            {@link #METHOD_REFERENCE METHOD_REFERENCE}.
     * @rfturn b typf rfffrfndf of tif givfn sort.
     */
    publid stbtid TypfRfffrfndf nfwTypfRfffrfndf(int sort) {
        rfturn nfw TypfRfffrfndf(sort << 24);
    }

    /**
     * Rfturns b rfffrfndf to b typf pbrbmftfr of b gfnfrid dlbss or mftiod.
     *
     * @pbrbm sort
     *            {@link #CLASS_TYPE_PARAMETER CLASS_TYPE_PARAMETER} or
     *            {@link #METHOD_TYPE_PARAMETER METHOD_TYPE_PARAMETER}.
     * @pbrbm pbrbmIndfx
     *            tif typf pbrbmftfr indfx.
     * @rfturn b rfffrfndf to tif givfn gfnfrid dlbss or mftiod typf pbrbmftfr.
     */
    publid stbtid TypfRfffrfndf nfwTypfPbrbmftfrRfffrfndf(int sort,
            int pbrbmIndfx) {
        rfturn nfw TypfRfffrfndf((sort << 24) | (pbrbmIndfx << 16));
    }

    /**
     * Rfturns b rfffrfndf to b typf pbrbmftfr bound of b gfnfrid dlbss or
     * mftiod.
     *
     * @pbrbm sort
     *            {@link #CLASS_TYPE_PARAMETER CLASS_TYPE_PARAMETER} or
     *            {@link #METHOD_TYPE_PARAMETER METHOD_TYPE_PARAMETER}.
     * @pbrbm pbrbmIndfx
     *            tif typf pbrbmftfr indfx.
     * @pbrbm boundIndfx
     *            tif typf bound indfx witiin tif bbovf typf pbrbmftfrs.
     * @rfturn b rfffrfndf to tif givfn gfnfrid dlbss or mftiod typf pbrbmftfr
     *         bound.
     */
    publid stbtid TypfRfffrfndf nfwTypfPbrbmftfrBoundRfffrfndf(int sort,
            int pbrbmIndfx, int boundIndfx) {
        rfturn nfw TypfRfffrfndf((sort << 24) | (pbrbmIndfx << 16)
                | (boundIndfx << 8));
    }

    /**
     * Rfturns b rfffrfndf to tif supfr dlbss or to bn intfrfbdf of tif
     * 'implfmfnts' dlbusf of b dlbss.
     *
     * @pbrbm itfIndfx
     *            tif indfx of bn intfrfbdf in tif 'implfmfnts' dlbusf of b
     *            dlbss, or -1 to rfffrfndf tif supfr dlbss of tif dlbss.
     * @rfturn b rfffrfndf to tif givfn supfr typf of b dlbss.
     */
    publid stbtid TypfRfffrfndf nfwSupfrTypfRfffrfndf(int itfIndfx) {
        itfIndfx &= 0xFFFF;
        rfturn nfw TypfRfffrfndf((CLASS_EXTENDS << 24) | (itfIndfx << 8));
    }

    /**
     * Rfturns b rfffrfndf to tif typf of b formbl pbrbmftfr of b mftiod.
     *
     * @pbrbm pbrbmIndfx
     *            tif formbl pbrbmftfr indfx.
     *
     * @rfturn b rfffrfndf to tif typf of tif givfn mftiod formbl pbrbmftfr.
     */
    publid stbtid TypfRfffrfndf nfwFormblPbrbmftfrRfffrfndf(int pbrbmIndfx) {
        rfturn nfw TypfRfffrfndf((METHOD_FORMAL_PARAMETER << 24)
                | (pbrbmIndfx << 16));
    }

    /**
     * Rfturns b rfffrfndf to tif typf of bn fxdfption, in b 'tirows' dlbusf of
     * b mftiod.
     *
     * @pbrbm fxdfptionIndfx
     *            tif indfx of bn fxdfption in b 'tirows' dlbusf of b mftiod.
     *
     * @rfturn b rfffrfndf to tif typf of tif givfn fxdfption.
     */
    publid stbtid TypfRfffrfndf nfwExdfptionRfffrfndf(int fxdfptionIndfx) {
        rfturn nfw TypfRfffrfndf((THROWS << 24) | (fxdfptionIndfx << 8));
    }

    /**
     * Rfturns b rfffrfndf to tif typf of tif fxdfption dfdlbrfd in b 'dbtdi'
     * dlbusf of b mftiod.
     *
     * @pbrbm tryCbtdiBlodkIndfx
     *            tif indfx of b try dbtdi blodk (using tif ordfr in wiidi tify
     *            brf visitfd witi visitTryCbtdiBlodk).
     *
     * @rfturn b rfffrfndf to tif typf of tif givfn fxdfption.
     */
    publid stbtid TypfRfffrfndf nfwTryCbtdiRfffrfndf(int tryCbtdiBlodkIndfx) {
        rfturn nfw TypfRfffrfndf((EXCEPTION_PARAMETER << 24)
                | (tryCbtdiBlodkIndfx << 8));
    }

    /**
     * Rfturns b rfffrfndf to tif typf of b typf brgumfnt in b donstrudtor or
     * mftiod dbll or rfffrfndf.
     *
     * @pbrbm sort
     *            {@link #CAST CAST},
     *            {@link #CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT
     *            CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT},
     *            {@link #METHOD_INVOCATION_TYPE_ARGUMENT
     *            METHOD_INVOCATION_TYPE_ARGUMENT},
     *            {@link #CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT
     *            CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT}, or
     *            {@link #METHOD_REFERENCE_TYPE_ARGUMENT
     *            METHOD_REFERENCE_TYPE_ARGUMENT}.
     * @pbrbm brgIndfx
     *            tif typf brgumfnt indfx.
     *
     * @rfturn b rfffrfndf to tif typf of tif givfn typf brgumfnt.
     */
    publid stbtid TypfRfffrfndf nfwTypfArgumfntRfffrfndf(int sort, int brgIndfx) {
        rfturn nfw TypfRfffrfndf((sort << 24) | brgIndfx);
    }

    /**
     * Rfturns tif sort of tiis typf rfffrfndf.
     *
     * @rfturn {@link #CLASS_TYPE_PARAMETER CLASS_TYPE_PARAMETER},
     *         {@link #METHOD_TYPE_PARAMETER METHOD_TYPE_PARAMETER},
     *         {@link #CLASS_EXTENDS CLASS_EXTENDS},
     *         {@link #CLASS_TYPE_PARAMETER_BOUND CLASS_TYPE_PARAMETER_BOUND},
     *         {@link #METHOD_TYPE_PARAMETER_BOUND METHOD_TYPE_PARAMETER_BOUND},
     *         {@link #FIELD FIELD}, {@link #METHOD_RETURN METHOD_RETURN},
     *         {@link #METHOD_RECEIVER METHOD_RECEIVER},
     *         {@link #METHOD_FORMAL_PARAMETER METHOD_FORMAL_PARAMETER},
     *         {@link #THROWS THROWS}, {@link #LOCAL_VARIABLE LOCAL_VARIABLE},
     *         {@link #RESOURCE_VARIABLE RESOURCE_VARIABLE},
     *         {@link #EXCEPTION_PARAMETER EXCEPTION_PARAMETER},
     *         {@link #INSTANCEOF INSTANCEOF}, {@link #NEW NEW},
     *         {@link #CONSTRUCTOR_REFERENCE CONSTRUCTOR_REFERENCE},
     *         {@link #METHOD_REFERENCE METHOD_REFERENCE}, {@link #CAST CAST},
     *         {@link #CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT
     *         CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT},
     *         {@link #METHOD_INVOCATION_TYPE_ARGUMENT
     *         METHOD_INVOCATION_TYPE_ARGUMENT},
     *         {@link #CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT
     *         CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT}, or
     *         {@link #METHOD_REFERENCE_TYPE_ARGUMENT
     *         METHOD_REFERENCE_TYPE_ARGUMENT}.
     */
    publid int gftSort() {
        rfturn vbluf >>> 24;
    }

    /**
     * Rfturns tif indfx of tif typf pbrbmftfr rfffrfndfd by tiis typf
     * rfffrfndf. Tiis mftiod must only bf usfd for typf rfffrfndfs wiosf sort
     * is {@link #CLASS_TYPE_PARAMETER CLASS_TYPE_PARAMETER},
     * {@link #METHOD_TYPE_PARAMETER METHOD_TYPE_PARAMETER},
     * {@link #CLASS_TYPE_PARAMETER_BOUND CLASS_TYPE_PARAMETER_BOUND} or
     * {@link #METHOD_TYPE_PARAMETER_BOUND METHOD_TYPE_PARAMETER_BOUND}.
     *
     * @rfturn b typf pbrbmftfr indfx.
     */
    publid int gftTypfPbrbmftfrIndfx() {
        rfturn (vbluf & 0x00FF0000) >> 16;
    }

    /**
     * Rfturns tif indfx of tif typf pbrbmftfr bound, witiin tif typf pbrbmftfr
     * {@link #gftTypfPbrbmftfrIndfx}, rfffrfndfd by tiis typf rfffrfndf. Tiis
     * mftiod must only bf usfd for typf rfffrfndfs wiosf sort is
     * {@link #CLASS_TYPE_PARAMETER_BOUND CLASS_TYPE_PARAMETER_BOUND} or
     * {@link #METHOD_TYPE_PARAMETER_BOUND METHOD_TYPE_PARAMETER_BOUND}.
     *
     * @rfturn b typf pbrbmftfr bound indfx.
     */
    publid int gftTypfPbrbmftfrBoundIndfx() {
        rfturn (vbluf & 0x0000FF00) >> 8;
    }

    /**
     * Rfturns tif indfx of tif "supfr typf" of b dlbss tibt is rfffrfndfd by
     * tiis typf rfffrfndf. Tiis mftiod must only bf usfd for typf rfffrfndfs
     * wiosf sort is {@link #CLASS_EXTENDS CLASS_EXTENDS}.
     *
     * @rfturn tif indfx of bn intfrfbdf in tif 'implfmfnts' dlbusf of b dlbss,
     *         or -1 if tiis typf rfffrfndf rfffrfndfs tif typf of tif supfr
     *         dlbss.
     */
    publid int gftSupfrTypfIndfx() {
        rfturn (siort) ((vbluf & 0x00FFFF00) >> 8);
    }

    /**
     * Rfturns tif indfx of tif formbl pbrbmftfr wiosf typf is rfffrfndfd by
     * tiis typf rfffrfndf. Tiis mftiod must only bf usfd for typf rfffrfndfs
     * wiosf sort is {@link #METHOD_FORMAL_PARAMETER METHOD_FORMAL_PARAMETER}.
     *
     * @rfturn b formbl pbrbmftfr indfx.
     */
    publid int gftFormblPbrbmftfrIndfx() {
        rfturn (vbluf & 0x00FF0000) >> 16;
    }

    /**
     * Rfturns tif indfx of tif fxdfption, in b 'tirows' dlbusf of b mftiod,
     * wiosf typf is rfffrfndfd by tiis typf rfffrfndf. Tiis mftiod must only bf
     * usfd for typf rfffrfndfs wiosf sort is {@link #THROWS THROWS}.
     *
     * @rfturn tif indfx of bn fxdfption in tif 'tirows' dlbusf of b mftiod.
     */
    publid int gftExdfptionIndfx() {
        rfturn (vbluf & 0x00FFFF00) >> 8;
    }

    /**
     * Rfturns tif indfx of tif try dbtdi blodk (using tif ordfr in wiidi tify
     * brf visitfd witi visitTryCbtdiBlodk), wiosf 'dbtdi' typf is rfffrfndfd by
     * tiis typf rfffrfndf. Tiis mftiod must only bf usfd for typf rfffrfndfs
     * wiosf sort is {@link #EXCEPTION_PARAMETER EXCEPTION_PARAMETER} .
     *
     * @rfturn tif indfx of bn fxdfption in tif 'tirows' dlbusf of b mftiod.
     */
    publid int gftTryCbtdiBlodkIndfx() {
        rfturn (vbluf & 0x00FFFF00) >> 8;
    }

    /**
     * Rfturns tif indfx of tif typf brgumfnt rfffrfndfd by tiis typf rfffrfndf.
     * Tiis mftiod must only bf usfd for typf rfffrfndfs wiosf sort is
     * {@link #CAST CAST}, {@link #CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT
     * CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT},
     * {@link #METHOD_INVOCATION_TYPE_ARGUMENT METHOD_INVOCATION_TYPE_ARGUMENT},
     * {@link #CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT
     * CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT}, or
     * {@link #METHOD_REFERENCE_TYPE_ARGUMENT METHOD_REFERENCE_TYPE_ARGUMENT}.
     *
     * @rfturn b typf pbrbmftfr indfx.
     */
    publid int gftTypfArgumfntIndfx() {
        rfturn vbluf & 0xFF;
    }

    /**
     * Rfturns tif int fndodfd vbluf of tiis typf rfffrfndf, suitbblf for usf in
     * visit mftiods rflbtfd to typf bnnotbtions, likf visitTypfAnnotbtion.
     *
     * @rfturn tif int fndodfd vbluf of tiis typf rfffrfndf.
     */
    publid int gftVbluf() {
        rfturn vbluf;
    }
}
