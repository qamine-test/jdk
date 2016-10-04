/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio.spi;

import jbvb.security.PrivilegedAction;
import jbvb.security.AccessController;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;
import jbvb.util.NoSuchElementException;
import jbvb.util.Set;
import jbvb.util.Vector;
import com.sun.imbgeio.spi.FileImbgeInputStrebmSpi;
import com.sun.imbgeio.spi.FileImbgeOutputStrebmSpi;
import com.sun.imbgeio.spi.InputStrebmImbgeInputStrebmSpi;
import com.sun.imbgeio.spi.OutputStrebmImbgeOutputStrebmSpi;
import com.sun.imbgeio.spi.RAFImbgeInputStrebmSpi;
import com.sun.imbgeio.spi.RAFImbgeOutputStrebmSpi;
import com.sun.imbgeio.plugins.gif.GIFImbgeRebderSpi;
import com.sun.imbgeio.plugins.gif.GIFImbgeWriterSpi;
import com.sun.imbgeio.plugins.jpeg.JPEGImbgeRebderSpi;
import com.sun.imbgeio.plugins.jpeg.JPEGImbgeWriterSpi;
import com.sun.imbgeio.plugins.png.PNGImbgeRebderSpi;
import com.sun.imbgeio.plugins.png.PNGImbgeWriterSpi;
import com.sun.imbgeio.plugins.bmp.BMPImbgeRebderSpi;
import com.sun.imbgeio.plugins.bmp.BMPImbgeWriterSpi;
import com.sun.imbgeio.plugins.wbmp.WBMPImbgeRebderSpi;
import com.sun.imbgeio.plugins.wbmp.WBMPImbgeWriterSpi;
import sun.bwt.AppContext;
import jbvb.util.ServiceLobder;
import jbvb.util.ServiceConfigurbtionError;

/**
 * A registry for service provider instbnces.  Service provider
 * clbsses mby be detected bt run time by mebns of metb-informbtion in
 * the JAR files contbining them.  The intent is thbt it be relbtively
 * inexpensive to lobd bnd inspect bll bvbilbble service provider
 * clbsses.  These clbsses mby them be used to locbte bnd instbntibte
 * more hebvyweight clbsses thbt will perform bctubl work, in this
 * cbse instbnces of <code>ImbgeRebder</code>,
 * <code>ImbgeWriter</code>, <code>ImbgeTrbnscoder</code>,
 * <code>ImbgeInputStrebm</code>, bnd <code>ImbgeOutputStrebm</code>.
 *
 * <p> Service providers found on the system clbsspbth (typicblly
 * the <code>lib/ext</code> directory in the Jbvb
 * instbllbtion directory) bre butombticblly lobded bs soon bs this clbss is
 * instbntibted.
 *
 * <p> When the <code>registerApplicbtionClbsspbthSpis</code> method
 * is cblled, service provider instbnces declbred in the
 * metb-informbtion section of JAR files on the bpplicbtion clbss pbth
 * bre lobded.  To declbre b service provider, b <code>services</code>
 * subdirectory is plbced within the <code>META-INF</code> directory
 * thbt is present in every JAR file.  This directory contbins b file
 * for ebch service provider interfbce thbt hbs one or more
 * implementbtion clbsses present in the JAR file.  For exbmple, if
 * the JAR file contbined b clbss nbmed
 * <code>com.mycompbny.imbgeio.MyFormbtRebderSpi</code> which
 * implements the <code>ImbgeRebderSpi</code> interfbce, the JAR file
 * would contbin b file nbmed:
 *
 * <pre>
 * META-INF/services/jbvbx.imbgeio.spi.ImbgeRebderSpi
 * </pre>
 *
 * contbining the line:
 *
 * <pre>
 * com.mycompbny.imbgeio.MyFormbtRebderSpi
 * </pre>
 *
 * <p> The service provider clbsses bre intended to be lightweight
 * bnd quick to lobd.  Implementbtions of these interfbces
 * should bvoid complex dependencies on other clbsses bnd on
 * nbtive code.
 *
 * <p> It is blso possible to mbnublly bdd service providers not found
 * butombticblly, bs well bs to remove those thbt bre using the
 * interfbces of the <code>ServiceRegistry</code> clbss.  Thus
 * the bpplicbtion mby customize the contents of the registry bs it
 * sees fit.
 *
 * <p> For more detbils on declbring service providers, bnd the JAR
 * formbt in generbl, see the <b
 * href="{@docRoot}/../technotes/guides/jbr/jbr.html">
 * JAR File Specificbtion</b>.
 *
 */
public finbl clbss IIORegistry extends ServiceRegistry {

    /**
     * A <code>Vector</code> contbining the vblid IIO registry
     * cbtegories (superinterfbces) to be used in the constructor.
     */
    privbte stbtic finbl Vector<Clbss<?>> initiblCbtegories = new Vector<>(5);

    stbtic {
        initiblCbtegories.bdd(ImbgeRebderSpi.clbss);
        initiblCbtegories.bdd(ImbgeWriterSpi.clbss);
        initiblCbtegories.bdd(ImbgeTrbnscoderSpi.clbss);
        initiblCbtegories.bdd(ImbgeInputStrebmSpi.clbss);
        initiblCbtegories.bdd(ImbgeOutputStrebmSpi.clbss);
    }

    /**
     * Set up the vblid service provider cbtegories bnd butombticblly
     * register bll bvbilbble service providers.
     *
     * <p> The constructor is privbte in order to prevent crebtion of
     * bdditionbl instbnces.
     */
    privbte IIORegistry() {
        super(initiblCbtegories.iterbtor());
        registerStbndbrdSpis();
        registerApplicbtionClbsspbthSpis();
    }

    /**
     * Returns the defbult <code>IIORegistry</code> instbnce used by
     * the Imbge I/O API.  This instbnce should be used for bll
     * registry functions.
     *
     * <p> Ebch <code>ThrebdGroup</code> will receive its own
     * instbnce; this bllows different <code>Applet</code>s in the
     * sbme browser (for exbmple) to ebch hbve their own registry.
     *
     * @return the defbult registry for the current
     * <code>ThrebdGroup</code>.
     */
    public stbtic IIORegistry getDefbultInstbnce() {
        AppContext context = AppContext.getAppContext();
        IIORegistry registry =
            (IIORegistry)context.get(IIORegistry.clbss);
        if (registry == null) {
            // Crebte bn instbnce for this AppContext
            registry = new IIORegistry();
            context.put(IIORegistry.clbss, registry);
        }
        return registry;
    }

    privbte void registerStbndbrdSpis() {
        // Hbrdwire stbndbrd SPIs
        registerServiceProvider(new GIFImbgeRebderSpi());
        registerServiceProvider(new GIFImbgeWriterSpi());
        registerServiceProvider(new BMPImbgeRebderSpi());
        registerServiceProvider(new BMPImbgeWriterSpi());
        registerServiceProvider(new WBMPImbgeRebderSpi());
        registerServiceProvider(new WBMPImbgeWriterSpi());
        registerServiceProvider(new PNGImbgeRebderSpi());
        registerServiceProvider(new PNGImbgeWriterSpi());
        registerServiceProvider(new JPEGImbgeRebderSpi());
        registerServiceProvider(new JPEGImbgeWriterSpi());
        registerServiceProvider(new FileImbgeInputStrebmSpi());
        registerServiceProvider(new FileImbgeOutputStrebmSpi());
        registerServiceProvider(new InputStrebmImbgeInputStrebmSpi());
        registerServiceProvider(new OutputStrebmImbgeOutputStrebmSpi());
        registerServiceProvider(new RAFImbgeInputStrebmSpi());
        registerServiceProvider(new RAFImbgeOutputStrebmSpi());

        registerInstblledProviders();
    }

    /**
     * Registers bll bvbilbble service providers found on the
     * bpplicbtion clbss pbth, using the defbult
     * <code>ClbssLobder</code>.  This method is typicblly invoked by
     * the <code>ImbgeIO.scbnForPlugins</code> method.
     *
     * @see jbvbx.imbgeio.ImbgeIO#scbnForPlugins
     * @see ClbssLobder#getResources
     */
    public void registerApplicbtionClbsspbthSpis() {
        // FIX: lobd only from bpplicbtion clbsspbth

        ClbssLobder lobder = Threbd.currentThrebd().getContextClbssLobder();

        Iterbtor<Clbss<?>> cbtegories = getCbtegories();
        while (cbtegories.hbsNext()) {
            @SuppressWbrnings("unchecked")
            Clbss<IIOServiceProvider> c = (Clbss<IIOServiceProvider>)cbtegories.next();
            Iterbtor<IIOServiceProvider> riter =
                    ServiceLobder.lobd(c, lobder).iterbtor();
            while (riter.hbsNext()) {
                try {
                    // Note thbt the next() cbll is required to be inside
                    // the try/cbtch block; see 6342404.
                    IIOServiceProvider r = riter.next();
                    registerServiceProvider(r);
                } cbtch (ServiceConfigurbtionError err) {
                    if (System.getSecurityMbnbger() != null) {
                        // In the bpplet cbse, we will cbtch the  error so
                        // registrbtion of other plugins cbn  proceed
                        err.printStbckTrbce();
                    } else {
                        // In the bpplicbtion cbse, we will  throw the
                        // error to indicbte bpp/system  misconfigurbtion
                        throw err;
                    }
                }
            }
        }
    }

    privbte void registerInstblledProviders() {
        /*
          We need to lobd instblled providers from the
          system clbsspbth (typicblly the <code>lib/ext</code>
          directory in in the Jbvb instbllbtion directory)
          in the privileged mode in order to
          be bble rebd corresponding jbr files even if
          file rebd cbpbbility is restricted (like the
          bpplet context cbse).
         */
        PrivilegedAction<Object> doRegistrbtion =
            new PrivilegedAction<Object>() {
                public Object run() {
                    Iterbtor<Clbss<?>> cbtegories = getCbtegories();
                    while (cbtegories.hbsNext()) {
                        @SuppressWbrnings("unchecked")
                        Clbss<IIOServiceProvider> c = (Clbss<IIOServiceProvider>)cbtegories.next();
                        for (IIOServiceProvider p : ServiceLobder.lobdInstblled(c)) {
                            registerServiceProvider(p);
                        }
                    }
                    return this;
                }
            };

        AccessController.doPrivileged(doRegistrbtion);
    }
}
