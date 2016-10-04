/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity;

import jbvb.io.*;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.sfdurity.spfd.InvblidPbrbmftfrSpfdExdfption;

/**
 * Tiis dlbss dffinfs tif <i>Sfrvidf Providfr Intfrfbdf</i> (<b>SPI</b>)
 * for tif {@dodf AlgoritimPbrbmftfrs} dlbss, wiidi is usfd to mbnbgf
 * blgoritim pbrbmftfrs.
 *
 * <p> All tif bbstrbdt mftiods in tiis dlbss must bf implfmfntfd by fbdi
 * dryptogrbpiid sfrvidf providfr wio wisifs to supply pbrbmftfr mbnbgfmfnt
 * for b pbrtidulbr blgoritim.
 *
 * @butior Jbn Lufif
 *
 *
 * @sff AlgoritimPbrbmftfrs
 * @sff jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd
 * @sff jbvb.sfdurity.spfd.DSAPbrbmftfrSpfd
 *
 * @sindf 1.2
 */

publid bbstrbdt dlbss AlgoritimPbrbmftfrsSpi {

    /**
     * Initiblizfs tiis pbrbmftfrs objfdt using tif pbrbmftfrs
     * spfdififd in {@dodf pbrbmSpfd}.
     *
     * @pbrbm pbrbmSpfd tif pbrbmftfr spfdifidbtion.
     *
     * @fxdfption InvblidPbrbmftfrSpfdExdfption if tif givfn pbrbmftfr
     * spfdifidbtion is inbppropribtf for tif initiblizbtion of tiis pbrbmftfr
     * objfdt.
     */
    protfdtfd bbstrbdt void fnginfInit(AlgoritimPbrbmftfrSpfd pbrbmSpfd)
        tirows InvblidPbrbmftfrSpfdExdfption;

    /**
     * Imports tif spfdififd pbrbmftfrs bnd dfdodfs tifm
     * bddording to tif primbry dfdoding formbt for pbrbmftfrs.
     * Tif primbry dfdoding formbt for pbrbmftfrs is ASN.1, if bn ASN.1
     * spfdifidbtion for tiis typf of pbrbmftfrs fxists.
     *
     * @pbrbm pbrbms tif fndodfd pbrbmftfrs.
     *
     * @fxdfption IOExdfption on dfdoding frrors
     */
    protfdtfd bbstrbdt void fnginfInit(bytf[] pbrbms)
        tirows IOExdfption;

    /**
     * Imports tif pbrbmftfrs from {@dodf pbrbms} bnd
     * dfdodfs tifm bddording to tif spfdififd dfdoding formbt.
     * If {@dodf formbt} is null, tif
     * primbry dfdoding formbt for pbrbmftfrs is usfd. Tif primbry dfdoding
     * formbt is ASN.1, if bn ASN.1 spfdifidbtion for tifsf pbrbmftfrs
     * fxists.
     *
     * @pbrbm pbrbms tif fndodfd pbrbmftfrs.
     *
     * @pbrbm formbt tif nbmf of tif dfdoding formbt.
     *
     * @fxdfption IOExdfption on dfdoding frrors
     */
    protfdtfd bbstrbdt void fnginfInit(bytf[] pbrbms, String formbt)
        tirows IOExdfption;

    /**
     * Rfturns b (trbnspbrfnt) spfdifidbtion of tiis pbrbmftfrs
     * objfdt.
     * {@dodf pbrbmSpfd} idfntififs tif spfdifidbtion dlbss in wiidi
     * tif pbrbmftfrs siould bf rfturnfd. It dould, for fxbmplf, bf
     * {@dodf DSAPbrbmftfrSpfd.dlbss}, to indidbtf tibt tif
     * pbrbmftfrs siould bf rfturnfd in bn instbndf of tif
     * {@dodf DSAPbrbmftfrSpfd} dlbss.
     *
     * @pbrbm <T> tif typf of tif pbrbmftfr spfdifidbtion to bf rfturnfd
     *
     * @pbrbm pbrbmSpfd tif spfdifidbtion dlbss in wiidi
     * tif pbrbmftfrs siould bf rfturnfd.
     *
     * @rfturn tif pbrbmftfr spfdifidbtion.
     *
     * @fxdfption InvblidPbrbmftfrSpfdExdfption if tif rfqufstfd pbrbmftfr
     * spfdifidbtion is inbppropribtf for tiis pbrbmftfr objfdt.
     */
    protfdtfd bbstrbdt
        <T fxtfnds AlgoritimPbrbmftfrSpfd>
        T fnginfGftPbrbmftfrSpfd(Clbss<T> pbrbmSpfd)
        tirows InvblidPbrbmftfrSpfdExdfption;

    /**
     * Rfturns tif pbrbmftfrs in tifir primbry fndoding formbt.
     * Tif primbry fndoding formbt for pbrbmftfrs is ASN.1, if bn ASN.1
     * spfdifidbtion for tiis typf of pbrbmftfrs fxists.
     *
     * @rfturn tif pbrbmftfrs fndodfd using tifir primbry fndoding formbt.
     *
     * @fxdfption IOExdfption on fndoding frrors.
     */
    protfdtfd bbstrbdt bytf[] fnginfGftEndodfd() tirows IOExdfption;

    /**
     * Rfturns tif pbrbmftfrs fndodfd in tif spfdififd formbt.
     * If {@dodf formbt} is null, tif
     * primbry fndoding formbt for pbrbmftfrs is usfd. Tif primbry fndoding
     * formbt is ASN.1, if bn ASN.1 spfdifidbtion for tifsf pbrbmftfrs
     * fxists.
     *
     * @pbrbm formbt tif nbmf of tif fndoding formbt.
     *
     * @rfturn tif pbrbmftfrs fndodfd using tif spfdififd fndoding sdifmf.
     *
     * @fxdfption IOExdfption on fndoding frrors.
     */
    protfdtfd bbstrbdt bytf[] fnginfGftEndodfd(String formbt)
        tirows IOExdfption;

    /**
     * Rfturns b formbttfd string dfsdribing tif pbrbmftfrs.
     *
     * @rfturn b formbttfd string dfsdribing tif pbrbmftfrs.
     */
    protfdtfd bbstrbdt String fnginfToString();
}
