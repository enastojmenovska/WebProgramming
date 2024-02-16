package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserFullNameConverter
        implements AttributeConverter<UserFullName,String> {
    private static final String SEPARATOR = " ";
    @Override
    public String convertToDatabaseColumn(UserFullName userFullName) {
        if(userFullName == null){
            return null;
        }
        /*StringBuilder sb = new StringBuilder();
        if (userFullName.getSurname() != null && !userFullName.getSurname()
                .isEmpty()) {
            sb.append(userFullName.getSurname());
            sb.append(SEPARATOR);
        }

        if (userFullName.getName() != null
                && !userFullName.getName().isEmpty()) {
            sb.append(userFullName.getName());
        }

        return sb.toString();*/
        return userFullName.getName()+SEPARATOR+userFullName.getSurname();
    }

    @Override
    public UserFullName convertToEntityAttribute(String dbPersonName) {
        if (dbPersonName == null || dbPersonName.isEmpty()) {
            return null;
        }

        String[] pieces = dbPersonName.split(SEPARATOR);

        /*if (pieces == null || pieces.length == 0) {
            return null;
        }

        UserFullName userFullName = new UserFullName();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (dbPersonName.contains(SEPARATOR)) {
            userFullName.setSurname(firstPiece);

            if (pieces.length >= 2 && pieces[1] != null
                    && !pieces[1].isEmpty()) {
                userFullName.setName(pieces[1]);
            }
        } else {
            userFullName.setName(firstPiece);
        }

        return userFullName;*/
        UserFullName userFullName=new UserFullName();
        userFullName.setName(pieces[0]);
        userFullName.setSurname(pieces[1]);
        return userFullName;
    }
}
