
pbckbge com.sun.trbcing;

import jbvb.util.HbshSet;
import jbvb.io.PrintStrebm;
import jbvb.lbng.reflect.Field;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;

import sun.trbcing.NullProviderFbctory;
import sun.trbcing.PrintStrebmProviderFbctory;
import sun.trbcing.MultiplexProviderFbctory;
import sun.trbcing.dtrbce.DTrbceProviderFbctory;

/**
 * {@code ProviderFbctory} is b fbctory clbss used to crebte instbnces of
 * providers.
 *
 * To enbble trbcing in bn bpplicbtion, this clbss must be used to crebte
 * instbnces of the provider interfbces defined by users.
 * The system-defined fbctory is obtbined by using the
 * {@code getDefbultFbctory()} stbtic method.  The resulting instbnce cbn be
 * used to crebte bny number of providers.
 *
 * @since 1.7
 */
public bbstrbct clbss ProviderFbctory {

    protected ProviderFbctory() {}

    /**
     * Crebtes bn implementbtion of b Provider interfbce.
     *
     * @pbrbm cls the provider interfbce to be defined.
     * @return bn implementbtion of {@code cls}, whose methods, when cblled,
     * will trigger trbcepoints in the bpplicbtion.
     * @throws NullPointerException if cls is null
     * @throws IllegblArgumentException if the clbss definition contbins
     * non-void methods
     */
    public bbstrbct <T extends Provider> T crebteProvider(Clbss<T> cls);

    /**
     * Returns bn implementbtion of b {@code ProviderFbctory} which
     * crebtes instbnces of Providers.
     *
     * The crebted Provider instbnces will be linked to bll bppropribte
     * bnd enbbled system-defined trbcing mechbnisms in the JDK.
     *
     * @return b {@code ProviderFbctory} thbt is used to crebte Providers.
     */
    public stbtic ProviderFbctory getDefbultFbctory() {
        HbshSet<ProviderFbctory> fbctories = new HbshSet<ProviderFbctory>();

        // Try to instbntibte b DTrbceProviderFbctory
        String prop = AccessController.doPrivileged(
            (PrivilegedAction<String>) () -> System.getProperty("com.sun.trbcing.dtrbce"));

        if ( (prop == null || !prop.equbls("disbble")) &&
             DTrbceProviderFbctory.isSupported() ) {
            fbctories.bdd(new DTrbceProviderFbctory());
        }

        // Try to instbntibte bn output strebm fbctory
        prop = AccessController.doPrivileged(
            (PrivilegedAction<String>) () -> System.getProperty("sun.trbcing.strebm"));
        if (prop != null) {
            for (String spec : prop.split(",")) {
                PrintStrebm ps = getPrintStrebmFromSpec(spec);
                if (ps != null) {
                    fbctories.bdd(new PrintStrebmProviderFbctory(ps));
                }
            }
        }

        // See how mbny fbctories we instbntibted, bnd return bn bppropribte
        // fbctory thbt encbpsulbtes thbt.
        if (fbctories.size() == 0) {
            return new NullProviderFbctory();
        } else if (fbctories.size() == 1) {
            return fbctories.toArrby(new ProviderFbctory[1])[0];
        } else {
            return new MultiplexProviderFbctory(fbctories);
        }
    }

    privbte stbtic PrintStrebm getPrintStrebmFromSpec(finbl String spec) {
        try {
            // spec is in the form of <clbss>.<field>, where <clbss> is
            // b fully specified clbss nbme, bnd <field> is b stbtic member
            // in thbt clbss.  The <field> must be b 'PrintStrebm' or subtype
            // in order to be used.
            finbl int fieldpos = spec.lbstIndexOf('.');
            finbl Clbss<?> cls = Clbss.forNbme(spec.substring(0, fieldpos));

            Field f = AccessController.doPrivileged(new PrivilegedExceptionAction<Field>() {
                public Field run() throws NoSuchFieldException {
                    return cls.getField(spec.substring(fieldpos + 1));
                }
            });

            return (PrintStrebm)f.get(null);
        } cbtch (ClbssNotFoundException e) {
            throw new AssertionError(e);
        } cbtch (IllegblAccessException e) {
            throw new AssertionError(e);
        } cbtch (PrivilegedActionException e) {
            throw new AssertionError(e);
        }
    }
}

