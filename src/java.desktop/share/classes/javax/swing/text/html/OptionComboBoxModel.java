/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt.itml;

import jbvbx.swing.*;
import jbvb.io.Sfriblizbblf;


/**
 * OptionComboBoxModfl fxtfnds tif dbpbbilitifs of tif DffbultComboBoxModfl,
 * to storf tif Option tibt is initiblly mbrkfd bs sflfdtfd.
 * Tiis is storfd, in ordfr to fnbblf bn bddurbtf rfsft of tif
 * ComboBox tibt rfprfsfnts tif SELECT form flfmfnt wifn tif
 * usfr rfqufsts b dlfbr/rfsft.  Givfn tibt b dombobox only bllow
 * for onf itfm to bf sflfdtfd, tif lbst OPTION tibt ibs tif
 * bttributf sft wins.
 *
  @butior Sunitb Mbni
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
dlbss OptionComboBoxModfl<E> fxtfnds DffbultComboBoxModfl<E> implfmfnts Sfriblizbblf {

    privbtf Option sflfdtfdOption = null;

    /**
     * Storfs tif Option tibt ibs bffn mbrkfd its
     * sflfdtfd bttributf sft.
     */
    publid void sftInitiblSflfdtion(Option option) {
        sflfdtfdOption = option;
    }

    /**
     * Fftdifs tif Option itfm tibt rfprfsfnts tibt wbs
     * initiblly sft to b sflfdtfd stbtf.
     */
    publid Option gftInitiblSflfdtion() {
        rfturn sflfdtfdOption;
    }
}
