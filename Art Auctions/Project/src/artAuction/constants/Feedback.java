package artAuction.constants;

/**
 * Messages of feedback.
 * @author Catarina Padilha (65194) c.padilha@campus.fct.unl.pt
 * @author Nelio Lacerda (66039) n.lacerda@campus.fct.unl.pt
 */

public enum Feedback {
    ADD_USER("\nRegisto de utilizador executado.\n"),
    ADD_ARTIST("\nRegisto de artista executado.\n"),
    REMOVE_USER("\nRemocao de utilizador executada.\n"),
    ADD_WORK("\nRegisto de obra executado.\n"),
    INFO_USER("\n%s %s %d %s\n\n"), INFO_ARTIST("\n%s %s %s %d %s\n\n"),
    INFO_WORK("\n%s %s %d %d %s %s\n\n"),
    CREATE_AUCTION("\nRegisto de leilao executado.\n"),
    ADD_WORK_AUCTION("\nObra adicionada ao leilao.\n"),
    BID("\nProposta aceite.\n"), CLOSE_AUCTION("\nLeilao encerrado."),
    QUIT("\nObrigado. Ate a proxima.\n"), WORK_SOLD("%s %s %s %s %d\n"),
    LIST_AUCTION_WORKS("%s %s %d %d %s %s\n"), LIST_ARTIST_WORKS("%s %s %d %d\n"),
    LIST_BIDS_WORK("%s %s %d\n"), LIST_WORKS_BY_VALUE("%s %s %d %d %s %s\n"),
    WORK_NOT_SOLD("%s %s sem propostas de venda.\n"),

    // Exceptions:

    ARTIST_IN_AUCTION("\nArtista com obras em leilao.\n"),
    BELOW_PRICE("\nValor proposto abaixo do valor minimo.\n"),
    EMPTY_AUCTION("\nLeilao sem obras.\n"), EXISTENT_AUCTION("\nLeilao existente.\n"),
    EXISTENT_USER("\nUtilizador existente.\n"), EXISTENT_WORK("\nObra existente.\n"),
    NO_ARTIST_WORKS("\nArtista sem obras.\n"), NO_BIDS("\nObra sem propostas.\n"),
    NONEXISTENT_AUCTION("\nLeilao inexistente.\n"),
    NONEXISTENT_USER("\nUtilizador inexistente.\n"), NONEXISTENT_WORK("\nObra inexistente.\n"),
    NONEXISTENT_WORK_AUCTION("\nObra inexistente no leilao.\n"),
    NOT_AN_ARTIST("\nArtista inexistente.\n"),
    NO_WORKS_SOLD("\nNao existem obras ja vendidas em leilao.\n"),
    UNDERAGE("\nIdade inferior a 18 anos.\n"),
    USER_IN_AUCTION("\nUtilizador com propostas submetidas.\n");

    private final String message;

    Feedback(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
