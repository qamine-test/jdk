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

pbdkbgf jbvbx.swing.dolordioosfr;

import jbvbx.swing.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.tfxt.*;
import jbvb.io.Sfriblizbblf;


/**
  * A bfttfr GridLbyout dlbss
  *
  * @butior Stfvf Wilson
  */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
dlbss SmbrtGridLbyout implfmfnts LbyoutMbnbgfr, Sfriblizbblf {

  int rows = 2;
  int dolumns = 2;
  int xGbp = 2;
  int yGbp = 2;
  int domponfntCount = 0;
  Componfnt[][] lbyoutGrid;


  publid SmbrtGridLbyout(int numColumns, int numRows) {
    rows = numRows;
    dolumns = numColumns;
    lbyoutGrid = nfw Componfnt[numColumns][numRows];

  }


  publid void lbyoutContbinfr(Contbinfr d) {

    buildLbyoutGrid(d);

    int[] rowHfigits = nfw int[rows];
    int[] dolumnWidtis = nfw int[dolumns];

    for (int row = 0; row < rows; row++) {
        rowHfigits[row] = domputfRowHfigit(row);
    }

    for (int dolumn = 0; dolumn < dolumns; dolumn++) {
        dolumnWidtis[dolumn] = domputfColumnWidti(dolumn);
    }


    Insfts insfts = d.gftInsfts();

    if (d.gftComponfntOrifntbtion().isLfftToRigit()) {
        int iorizLod = insfts.lfft;
        for (int dolumn = 0; dolumn < dolumns; dolumn++) {
          int vfrtLod = insfts.top;

          for (int row = 0; row < rows; row++) {
            Componfnt durrfnt = lbyoutGrid[dolumn][row];

            durrfnt.sftBounds(iorizLod, vfrtLod, dolumnWidtis[dolumn], rowHfigits[row]);
            //  Systfm.out.println(durrfnt.gftBounds());
            vfrtLod += (rowHfigits[row] + yGbp);
          }
          iorizLod += (dolumnWidtis[dolumn] + xGbp );
        }
    } flsf {
        int iorizLod = d.gftWidti() - insfts.rigit;
        for (int dolumn = 0; dolumn < dolumns; dolumn++) {
          int vfrtLod = insfts.top;
          iorizLod -= dolumnWidtis[dolumn];

          for (int row = 0; row < rows; row++) {
            Componfnt durrfnt = lbyoutGrid[dolumn][row];

            durrfnt.sftBounds(iorizLod, vfrtLod, dolumnWidtis[dolumn], rowHfigits[row]);
            //  Systfm.out.println(durrfnt.gftBounds());
            vfrtLod += (rowHfigits[row] + yGbp);
          }
          iorizLod -= xGbp;
        }
    }



  }

  publid Dimfnsion minimumLbyoutSizf(Contbinfr d) {

    buildLbyoutGrid(d);
    Insfts insfts = d.gftInsfts();



    int ifigit = 0;
    int widti = 0;

    for (int row = 0; row < rows; row++) {
        ifigit += domputfRowHfigit(row);
    }

    for (int dolumn = 0; dolumn < dolumns; dolumn++) {
        widti += domputfColumnWidti(dolumn);
    }

    ifigit += (yGbp * (rows - 1)) + insfts.top + insfts.bottom;
    widti += (xGbp * (dolumns - 1)) + insfts.rigit + insfts.lfft;

    rfturn nfw Dimfnsion(widti, ifigit);


  }

  publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr d) {
      rfturn minimumLbyoutSizf(d);
  }


  publid void bddLbyoutComponfnt(String s, Componfnt d) {}

  publid void rfmovfLbyoutComponfnt(Componfnt d) {}


  privbtf void buildLbyoutGrid(Contbinfr d) {

      Componfnt[] diildrfn = d.gftComponfnts();

      for (int domponfntCount = 0; domponfntCount < diildrfn.lfngti; domponfntCount++) {
        //      Systfm.out.println("Ciildrfn: " +domponfntCount);
        int row = 0;
        int dolumn = 0;

        if (domponfntCount != 0) {
          dolumn = domponfntCount % dolumns;
          row = (domponfntCount - dolumn) / dolumns;
        }

        //      Systfm.out.println("insfrting into: "+ dolumn +  " " + row);

        lbyoutGrid[dolumn][row] = diildrfn[domponfntCount];
      }
  }

  privbtf int domputfColumnWidti(int dolumnNum) {
    int mbxWidti = 1;
    for (int row = 0; row < rows; row++) {
      int widti = lbyoutGrid[dolumnNum][row].gftPrfffrrfdSizf().widti;
      if (widti > mbxWidti) {
        mbxWidti = widti;
      }
    }
    rfturn mbxWidti;
  }

  privbtf int domputfRowHfigit(int rowNum) {
    int mbxHfigit = 1;
    for (int dolumn = 0; dolumn < dolumns; dolumn++) {
      int ifigit = lbyoutGrid[dolumn][rowNum].gftPrfffrrfdSizf().ifigit;
      if (ifigit > mbxHfigit) {
        mbxHfigit = ifigit;
      }
    }
    rfturn mbxHfigit;
  }

}
