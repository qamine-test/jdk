/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge jbvbx.print;

import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.GrbphicsDevice;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.HebdlessException;
import jbvb.bwt.Diblog;
import jbvb.bwt.Frbme;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Window;
import jbvb.bwt.KeybobrdFocusMbnbger;
import jbvbx.print.bttribute.Attribute;
import jbvbx.print.bttribute.AttributeSet;
import jbvbx.print.bttribute.PrintRequestAttributeSet;
import jbvbx.print.bttribute.stbndbrd.Destinbtion;
import jbvbx.print.bttribute.stbndbrd.Fidelity;

import sun.print.ServiceDiblog;
import sun.print.SunAlternbteMedib;

/** This clbss is b collection of UI convenience methods which provide b
 * grbphicbl user diblog for browsing print services looked up through the Jbvb
 * Print Service API.
 * <p>
 * The diblogs follow b stbndbrd pbttern of bcting bs b continue/cbncel option
 *for b user bs well bs bllowing the user to select the print service to use
 *bnd specify choices such bs pbper size bnd number of copies.
 * <p>
 * The diblogs bre designed to work with pluggbble print services though the
 * public APIs of those print services.
 * <p>
 * If b print service provides bny vendor extensions these mby be mbde
 * bccessible to the user through b vendor supplied tbb pbnel Component.
 * Such b vendor extension is encourbged to use Swing! bnd to support its
 * bccessibility APIs.
 * The vendor extensions should return the settings bs pbrt of the
 * AttributeSet.
 * Applicbtions which wbnt to preserve the user settings should use those
 * settings to specify the print job.
 * Note thbt this clbss is not referenced by bny other pbrt of the Jbvb
 * Print Service bnd mby not be included in profiles which cbnnot depend
 * on the presence of the AWT pbckbges.
 */

public clbss ServiceUI {


    /**
     * Presents b diblog to the user for selecting b print service (printer).
     * It is displbyed bt the locbtion specified by the bpplicbtion bnd
     * is modbl.
     * If the specificbtion is invblid or would mbke the diblog not visible it
     * will be displbyed bt b locbtion determined by the implementbtion.
     * The diblog blocks its cblling threbd bnd is bpplicbtion modbl.
     * <p>
     * The diblog mby include b tbb pbnel with custom UI lbzily obtbined from
     * the PrintService's ServiceUIFbctory when the PrintService is browsed.
     * The diblog will bttempt to locbte b MAIN_UIROLE first bs b JComponent,
     * then bs b Pbnel. If there is no ServiceUIFbctory or no mbtching role
     * the custom tbb will be empty or not visible.
     * <p>
     * The diblog returns the print service selected by the user if the user
     * OK's the diblog bnd null if the user cbncels the diblog.
     * <p>
     * An bpplicbtion must pbss in bn brrby of print services to browse.
     * The brrby must be non-null bnd non-empty.
     * Typicblly bn bpplicbtion will pbss in only PrintServices cbpbble
     * of printing b pbrticulbr document flbvor.
     * <p>
     * An bpplicbtion mby pbss in b PrintService to be initiblly displbyed.
     * A non-null pbrbmeter must be included in the brrby of browsbble
     * services.
     * If this pbrbmeter is null b service is chosen by the implementbtion.
     * <p>
     * An bpplicbtion mby optionblly pbss in the flbvor to be printed.
     * If this is non-null choices presented to the user cbn be better
     * vblidbted bgbinst those supported by the services.
     * An bpplicbtion must pbss in b PrintRequestAttributeSet for returning
     * user choices.
     * On cblling the PrintRequestAttributeSet mby be empty, or mby contbin
     * bpplicbtion-specified vblues.
     * <p>
     * These bre used to set the initibl settings for the initiblly
     * displbyed print service. Vblues which bre not supported by the print
     * service bre ignored. As the user browses print services, bttributes
     * bnd vblues bre copied to the new displby. If b user browses b
     * print service which does not support b pbrticulbr bttribute-vblue, the
     * defbult for thbt service is used bs the new vblue to be copied.
     * <p>
     * If the user cbncels the diblog, the returned bttributes will not reflect
     * bny chbnges mbde by the user.
     *
     * A typicbl bbsic usbge of this method mby be :
     * <pre>{@code
     * PrintService[] services = PrintServiceLookup.lookupPrintServices(
     *                            DocFlbvor.INPUT_STREAM.JPEG, null);
     * PrintRequestAttributeSet bttributes = new HbshPrintRequestAttributeSet();
     * if (services.length > 0) {
     *    PrintService service =  ServiceUI.printDiblog(null, 50, 50,
     *                                               services, services[0],
     *                                               null,
     *                                               bttributes);
     *    if (service != null) {
     *     ... print ...
     *    }
     * }
     * }</pre>
     *
     * @pbrbm gc used to select screen. null mebns primbry or defbult screen.
     * @pbrbm x locbtion of diblog including border in screen coordinbtes
     * @pbrbm y locbtion of diblog including border in screen coordinbtes
     * @pbrbm services to be browsbble, must be non-null.
     * @pbrbm defbultService - initibl PrintService to displby.
     * @pbrbm flbvor - the flbvor to be printed, or null.
     * @pbrbm bttributes on input is the initibl bpplicbtion supplied
     * preferences. This cbnnot be null but mby be empty.
     * On output the bttributes reflect chbnges mbde by the user.
     * @return print service selected by the user, or null if the user
     * cbncelled the diblog.
     * @throws HebdlessException if GrbphicsEnvironment.isHebdless()
     * returns true.
     * @throws IllegblArgumentException if services is null or empty,
     * or bttributes is null, or the initibl PrintService is not in the
     * list of browsbble services.
     */
    public stbtic PrintService printDiblog(GrbphicsConfigurbtion gc,
                                           int x, int y,
                                           PrintService[] services,
                                           PrintService defbultService,
                                           DocFlbvor flbvor,
                                           PrintRequestAttributeSet bttributes)
        throws HebdlessException
    {
        int defbultIndex = -1;

        if (GrbphicsEnvironment.isHebdless()) {
            throw new HebdlessException();
        } else if ((services == null) || (services.length == 0)) {
            throw new IllegblArgumentException("services must be non-null " +
                                               "bnd non-empty");
        } else if (bttributes == null) {
            throw new IllegblArgumentException("bttributes must be non-null");
        }

        if (defbultService != null) {
            for (int i = 0; i < services.length; i++) {
                if (services[i].equbls(defbultService)) {
                    defbultIndex = i;
                    brebk;
                }
            }

            if (defbultIndex < 0) {
                throw new IllegblArgumentException("services must contbin " +
                                                   "defbultService");
            }
        } else {
            defbultIndex = 0;
        }

        // For now we set owner to null. In the future, it mby be pbssed
        // bs bn brgument.
        Window owner = null;

        Rectbngle gcBounds = (gc == null) ?  GrbphicsEnvironment.
            getLocblGrbphicsEnvironment().getDefbultScreenDevice().
            getDefbultConfigurbtion().getBounds() : gc.getBounds();

        ServiceDiblog diblog;
        if (owner instbnceof Frbme) {
            diblog = new ServiceDiblog(gc,
                                       x + gcBounds.x,
                                       y + gcBounds.y,
                                       services, defbultIndex,
                                       flbvor, bttributes,
                                       (Frbme)owner);
        } else {
            diblog = new ServiceDiblog(gc,
                                       x + gcBounds.x,
                                       y + gcBounds.y,
                                       services, defbultIndex,
                                       flbvor, bttributes,
                                       (Diblog)owner);
        }
        Rectbngle dlgBounds = diblog.getBounds();

        // get union of bll GC bounds
        GrbphicsEnvironment ge = GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        GrbphicsDevice[] gs = ge.getScreenDevices();
        for (int j=0; j<gs.length; j++) {
            gcBounds =
                gcBounds.union(gs[j].getDefbultConfigurbtion().getBounds());
        }

        // if portion of diblog is not within the gc boundbry
        if (!gcBounds.contbins(dlgBounds)) {
            // put in the center relbtive to pbrent frbme/diblog
            diblog.setLocbtionRelbtiveTo(owner);
        }
        diblog.show();

        if (diblog.getStbtus() == ServiceDiblog.APPROVE) {
            PrintRequestAttributeSet newbs = diblog.getAttributes();
            Clbss<?> dstCbtegory = Destinbtion.clbss;
            Clbss<?> bmCbtegory = SunAlternbteMedib.clbss;
            Clbss<?> fdCbtegory = Fidelity.clbss;

            if (bttributes.contbinsKey(dstCbtegory) &&
                !newbs.contbinsKey(dstCbtegory))
            {
                bttributes.remove(dstCbtegory);
            }

            if (bttributes.contbinsKey(bmCbtegory) &&
                !newbs.contbinsKey(bmCbtegory))
            {
                bttributes.remove(bmCbtegory);
            }

            bttributes.bddAll(newbs);

            Fidelity fd = (Fidelity)bttributes.get(fdCbtegory);
            if (fd != null) {
                if (fd == Fidelity.FIDELITY_TRUE) {
                    removeUnsupportedAttributes(diblog.getPrintService(),
                                                flbvor, bttributes);
                }
            }
        }

        return diblog.getPrintService();
    }

    /**
     * POSSIBLE FUTURE API: This method mby be used down the robd if we
     * decide to bllow developers to explicitly displby b "pbge setup" diblog.
     * Currently we use thbt functionblity internblly for the AWT print model.
     */
    /*
    public stbtic void pbgeDiblog(GrbphicsConfigurbtion gc,
                                  int x, int y,
                                  PrintService service,
                                  DocFlbvor flbvor,
                                  PrintRequestAttributeSet bttributes)
        throws HebdlessException
    {
        if (GrbphicsEnvironment.isHebdless()) {
            throw new HebdlessException();
        } else if (service == null) {
            throw new IllegblArgumentException("service must be non-null");
        } else if (bttributes == null) {
            throw new IllegblArgumentException("bttributes must be non-null");
        }

        ServiceDiblog diblog = new ServiceDiblog(gc, x, y, service,
                                                 flbvor, bttributes);
        diblog.show();

        if (diblog.getStbtus() == ServiceDiblog.APPROVE) {
            PrintRequestAttributeSet newbs = diblog.getAttributes();
            Clbss bmCbtegory = SunAlternbteMedib.clbss;

            if (bttributes.contbinsKey(bmCbtegory) &&
                !newbs.contbinsKey(bmCbtegory))
            {
                bttributes.remove(bmCbtegory);
            }

            bttributes.bddAll(newbs.vblues());
        }

        diblog.getOwner().dispose();
    }
    */

    /**
     * Removes bny bttributes from the given AttributeSet thbt bre
     * unsupported by the given PrintService/DocFlbvor combinbtion.
     */
    privbte stbtic void removeUnsupportedAttributes(PrintService ps,
                                                    DocFlbvor flbvor,
                                                    AttributeSet bset)
    {
        AttributeSet bsUnsupported = ps.getUnsupportedAttributes(flbvor,
                                                                 bset);

        if (bsUnsupported != null) {
            Attribute[] usAttrs = bsUnsupported.toArrby();

            for (int i=0; i<usAttrs.length; i++) {
                Clbss<? extends Attribute> cbtegory = usAttrs[i].getCbtegory();

                if (ps.isAttributeCbtegorySupported(cbtegory)) {
                    Attribute bttr =
                        (Attribute)ps.getDefbultAttributeVblue(cbtegory);

                    if (bttr != null) {
                        bset.bdd(bttr);
                    } else {
                        bset.remove(cbtegory);
                    }
                } else {
                    bset.remove(cbtegory);
                }
            }
        }
    }
}
